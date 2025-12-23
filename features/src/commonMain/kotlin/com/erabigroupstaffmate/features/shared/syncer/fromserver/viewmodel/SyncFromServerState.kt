package com.erabigroupstaffmate.features.shared.syncer.fromserver.viewmodel

import com.erabigroupstaffmate.uihub.components.date.WheelPickerMonthState

data class SyncFromServerState(
    val isSyncing: Boolean = false,
    val selectedMonth: WheelPickerMonthState = WheelPickerMonthState(),
    val selectedYear: Int = 2025,
    val showDatePicker: Boolean = false,
)
