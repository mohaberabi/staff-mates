package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.Serializable

@Serializable
data class AuthKeyModel(
    val name: String = "",
    val code: String = "",
    val isAdmin: Boolean = false,
    val roles: List<Int> = emptyList()
)
