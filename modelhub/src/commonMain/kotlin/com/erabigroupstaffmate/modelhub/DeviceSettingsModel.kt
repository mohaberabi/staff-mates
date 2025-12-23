package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.Serializable

@Serializable
data class DeviceSettingsModel(
    val chainId: String,
    val branchId: String
)
