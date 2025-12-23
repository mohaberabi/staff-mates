package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.Serializable

@Serializable

data class StaffBorrowModel(
    val id: String,
    val borrowAtMillis: Long,
    val staffId: String,
    val staffName: String,
    val amount: Double,
    val reason: String,
    val recordYear: String,
    val recordMonth: String,
    val branch: String,
    val chain: String
)
