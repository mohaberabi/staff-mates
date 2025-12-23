package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.Serializable


@Serializable
data class UserDataModel(
    val email: String,
    val uid: String,
)
