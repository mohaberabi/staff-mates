package com.erabigroupstaffmate.features.shared.appmode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.appmode.WriteAppModeUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.ReadDeviceSettingsUseCase
import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.features.shared.appmode.screen.loadAppModulesAtRuntime
import com.erabigroupstaffmate.modelhub.AppMode
import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerManager
import com.erabigroupstaffmate.uihub.uimodel.extensions.stringRes
import com.erabigroupstaffmate.utility.extensions.onFailureNonCancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppModeViewModel(
    private val writeAppModeUseCase: WriteAppModeUseCase,
    private val syncFromServerManager: SyncFromServerManager,
    private val readDeviceSettingsUseCase: ReadDeviceSettingsUseCase,
    private val erabiTime: ErabiTime,
) : ViewModel() {


    private val _events = Channel<AppModeEvents>()
    val events = _events.receiveAsFlow()

    private val _state = MutableStateFlow(AppModeState())
    val state = _state.asStateFlow()

    fun selectMode(mode: AppMode) {
        _state.update { it.copy(selectedMode = mode) }
    }


    fun confirm() {
        viewModelScope.launch {
            val mode = _state.value.selectedMode
            loadAppModulesAtRuntime(mode = mode)
            writeAppModeUseCase(mode)
            startSync()
        }
    }


    private suspend fun startSync() {

        val settingsVal = readDeviceSettingsUseCase()
            .firstOrNull()
            ?: run { return }

        val now = erabiTime.getNowDateTimeInErabiZone()
        _state.update { it.copy(isSyncing = true) }
        runCatching {
            val payload = PayrollPayload(
                branchId = settingsVal.branchId,
                chainId = settingsVal.chainId,
                year = now.year.toString(),
                month = now.monthNumber.toString()
            )
            syncFromServerManager.syncAllData(payload)
        }.onFailureNonCancel {
            _events.send(AppModeEvents.Error(it.stringRes()))
        }.onSuccess {
            _events.send(AppModeEvents.SyncedData)
        }
        _state.update { it.copy(isSyncing = false) }

    }
}