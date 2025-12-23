package com.erabigroupstaffmate.network.domain

interface DeviceSettingsRemoteDataSource {


    suspend fun validateDeviceSettings(
        chain: String,
        branch: String
    )
}