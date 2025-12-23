package com.erabigroupstaffmate.navigation

import kotlinx.serialization.Serializable

@Serializable
data class AddDeductRoute(
    val staffJson: String,
    val staffDeductTypeName: String,
)

