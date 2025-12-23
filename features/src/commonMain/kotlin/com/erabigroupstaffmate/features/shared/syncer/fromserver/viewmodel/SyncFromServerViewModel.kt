package com.erabigroupstaffmate.features.shared.syncer.fromserver.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerManager
import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.ReadDeviceSettingsUseCase
import com.erabigroupstaffmate.uihub.components.date.WheelPickerMonthState
import com.erabigroupstaffmate.uihub.components.snackbar.SnackbarMessage
import com.erabigroupstaffmate.utility.extensions.onFailureNonCancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SyncFromServerViewModel(
    private val syncFromServerManager: SyncFromServerManager,
    private val readDeviceSettingsUseCase: ReadDeviceSettingsUseCase,
    private val erabiTime: ErabiTime,
) : ViewModel() {


    private val now = erabiTime.getNowDateTimeInErabiZone()

    private val _state = MutableStateFlow(
        SyncFromServerState(
            selectedYear = now.year,
            selectedMonth = WheelPickerMonthState.fromMonth(month = now.monthNumber)
        )
    )
    val state = _state.asStateFlow()

    private val _messages = Channel<SnackbarMessage>()
    val messages = _messages.receiveAsFlow()

    fun onAction(action: SyncFromServerActions) {
        when (action) {
            SyncFromServerActions.StartSync -> startSync()
            is SyncFromServerActions.DateChanged -> dateChanged(
                year = action.selectedYear,
                month = action.selectedMonth
            )

            SyncFromServerActions.ToggleDatePicker -> togglePicker()
        }
    }

    private fun startSync() {
        viewModelScope.launch {
            val settingsVal = readDeviceSettingsUseCase().firstOrNull() ?: run {
                _messages.send(SnackbarMessage.Error(message = "Please activate device settings"))
                return@launch
            }
            val stateVal = _state.value
            _state.update { it.copy(isSyncing = true) }
            runCatching {
                val payload = PayrollPayload(
                    branchId = settingsVal.branchId,
                    chainId = settingsVal.chainId,
                    year = stateVal.selectedYear.toString(),
                    month = stateVal.selectedMonth.monthNumber.toString()
                )
                syncFromServerManager.syncAllData(payload)
            }.onFailureNonCancel {
                it.printStackTrace()
                _messages.send(
                    SnackbarMessage.Error(
                        message = it.message ?: "something went wrong"
                    )
                )
            }.onSuccess {
                _messages.send(SnackbarMessage.Done(message = "Syncing was done "))
            }
            _state.update { it.copy(isSyncing = false) }

        }
    }

    private fun togglePicker() = _state.update { it.copy(showDatePicker = !it.showDatePicker) }
    private fun dateChanged(
        year: Int,
        month: WheelPickerMonthState
    ) {
        _state.update { it.copy(selectedMonth = month, selectedYear = year) }
    }
}