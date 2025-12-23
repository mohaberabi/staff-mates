package com.erabigroupstaffmate.navigation

import kotlinx.serialization.Serializable


@Serializable
data class AddStaffRoute(
    val staffJson: String?
)