package com.erabigroupstaffmate.features.admin.adddeduct.viewmodel

import com.erabigroupstaffmate.modelhub.StaffDeductType
import com.erabigroupstaffmate.modelhub.StaffModel

data class AddDeductState(
    val loading: Boolean = false,
    val earnPerDay: Double,
    val staff: StaffModel,
    val amount: String = "",
    val reason: String = "",
    val type: StaffDeductType,
)

fun AddDeductState.canAdd() = amount.isNotBlank() && reason.isNotBlank()
