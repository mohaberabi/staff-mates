package com.erabigroupstaffmate.features.admin.payroll.viewmodel

import com.erabigroupstaffmate.uihub.components.date.WheelPickerMonthState

data class PayrollState(
    val isGenerating: Boolean = false,
    val selectedMonth: WheelPickerMonthState = WheelPickerMonthState(),
    val selectedYear: Int
)
