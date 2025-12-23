package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.Serializable

@Serializable
data class StaffDeductModel(
    val id: String,
    val staffId: String,
    val deductAtMillis: Long,
    val amount: Double,
    val reason: String,
    val recordYear: String,
    val recordMonth: String,
    val branch: String,
    val chain: String
)
