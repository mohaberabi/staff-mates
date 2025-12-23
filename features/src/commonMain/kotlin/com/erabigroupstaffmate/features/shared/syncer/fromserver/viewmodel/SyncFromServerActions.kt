package com.erabigroupstaffmate.features.shared.syncer.fromserver.viewmodel

import com.erabigroupstaffmate.uihub.components.date.WheelPickerMonthState

sealed interface SyncFromServerActions {
    data object StartSync : SyncFromServerActions
    data object ToggleDatePicker : SyncFromServerActions
    data class DateChanged(
        val selectedMonth: WheelPickerMonthState = WheelPickerMonthState(),
        val selectedYear: Int
    ) : SyncFromServerActions

}