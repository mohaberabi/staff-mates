package com.erabigroupstaffmate.features.shared.settings.viewmodel.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.appmode.ReadAppModeUseCase
import com.erabigroupstaffmate.modelhub.DeviceSettingsModel
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.ReadDeviceSettingsUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.WriteDeviceSettingsUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.user.ReadUserDataUseCase
import com.erabigroupstaffmate.modelhub.AppMode
import com.erabigroupstaffmate.uihub.components.snackbar.SnackbarMessage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountInfoViewModel(
    private val readDeviceSettingsUseCase: ReadDeviceSettingsUseCase,
    private val readAppModeUseCase: ReadAppModeUseCase,
    private val readUserDataUseCase: ReadUserDataUseCase,
) : ViewModel() {


    val state = combine(
        readAppModeUseCase().distinctUntilChanged(),
        readUserDataUseCase().distinctUntilChanged(),
        readDeviceSettingsUseCase().distinctUntilChanged()
    ) { mode, user, device ->
        AccountInfoState(
            mode = mode ?: AppMode.Unknown,
            email = user?.email ?: "",
            chain = device?.chainId ?: "",
            branch = device?.branchId ?: ""
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = AccountInfoState(),
        started = SharingStarted.Companion.WhileSubscribed()
    )


}