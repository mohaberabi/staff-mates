package com.erabigroup.erabigroupstaffmate.app.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.appmode.ReadAppModeUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.ReadDeviceSettingsUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.locale.ReadAppLanguageUseCase
import com.erabigroupstaffmate.features.shared.appmode.screen.loadAppModulesAtRuntime
import com.erabigroupstaffmate.modelhub.AppMode
import com.erabigroupstaffmate.navigation.routes.AdminRoute
import com.erabigroupstaffmate.navigation.routes.ChooseAppModeRoute
import com.erabigroupstaffmate.navigation.routes.KioskRoute
import com.erabigroupstaffmate.navigation.routes.LoginRoute
import com.erabigroupstaffmate.utility.localizations.changeLanguage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val readAppLanguageUseCase: ReadAppLanguageUseCase,
    private val readAppModeUseCase: ReadAppModeUseCase,
    private val readDeviceSettingsUseCase: ReadDeviceSettingsUseCase,
) : ViewModel() {

    private val _startRoute = MutableStateFlow<Any?>(null)
    val startRoute = _startRoute.asStateFlow()

    init {
        initializeStartRoute()
    }

    private fun getAppLanguage() = readAppLanguageUseCase()
        .distinctUntilChangedBy { it }
        .onEach { changeLanguage(it) }


    val state = getAppLanguage()
        .map<_, MainAppState> { MainAppState.Initialized(lang = it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MainAppState.Initializing
        )


    private fun initializeStartRoute() {
        viewModelScope.launch {
            runCatching {
                getStartRoute()
            }.onSuccess { route ->
                _startRoute.update { route }
            }.onFailure {
                _startRoute.update { LoginRoute }
            }
        }
    }

    private suspend fun getStartRoute(): Any {
        val settings = readDeviceSettingsUseCase()
            .firstOrNull()
            ?: return LoginRoute

        val appMode = readAppModeUseCase()
            .firstOrNull()
            ?: return ChooseAppModeRoute

        loadAppModulesAtRuntime(appMode)
        return when (appMode) {
            AppMode.Kiosk -> KioskRoute
            AppMode.Admin -> AdminRoute
            AppMode.Unknown -> LoginRoute
        }
    }


}