package com.erabigroupstaffmate.features.admin.staffdoc.viewmodel

import com.erabigroupstaffmate.uihub.components.date.WheelPickerMonthState

data class StaffDocState(
    val isGenerating: Boolean = false,
    val selectedMonth: WheelPickerMonthState = WheelPickerMonthState(),
    val selectedYear: Int
)
