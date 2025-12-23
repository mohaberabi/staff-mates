package com.erabigroupstaffmate.modelhub.uidmodel

data class ShiftLogUiModel(
    val id: String,
    val staffId: String,
    val checkInTime: String,
    val checkOutTime: String,
    val totalHours: String,
    val staffFullName: String,
)
