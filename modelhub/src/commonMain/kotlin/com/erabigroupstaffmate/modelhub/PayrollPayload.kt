package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.Serializable

@Serializable
data class PayrollPayload(
    val branchId: String,
    val chainId: String,
    val year: String,
    val month: String
)
