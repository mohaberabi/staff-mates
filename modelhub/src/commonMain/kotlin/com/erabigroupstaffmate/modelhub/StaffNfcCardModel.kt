package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.Serializable


@Serializable
data class StaffNfcCardModel(
    val staffId: String,
    val authKey: String
)
