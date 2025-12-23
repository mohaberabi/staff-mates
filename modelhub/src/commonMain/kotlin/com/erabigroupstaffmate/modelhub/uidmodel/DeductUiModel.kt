package com.erabigroupstaffmate.modelhub.uidmodel

data class DeductUiModel(
    val id: String,
    val staffId: String,
    val deductFormattedDate: String,
    val amount: Double,
    val reason: String,
)
