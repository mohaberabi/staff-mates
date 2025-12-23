package com.erabigroupstaffmate.core.domain.usecase.login

import com.erabigroupstaffmate.network.domain.DeviceSettingsRemoteDataSource

class ValidateDeviceSettingsUseCase(
    private val deviceSettingsRemoteDataSource: DeviceSettingsRemoteDataSource
) {
    suspend operator fun invoke(chain: String, branch: String) {
        deviceSettingsRemoteDataSource.validateDeviceSettings(chain = chain, branch = branch)
    }
}