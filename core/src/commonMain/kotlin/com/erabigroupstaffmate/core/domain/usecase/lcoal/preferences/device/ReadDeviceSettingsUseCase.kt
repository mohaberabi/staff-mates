package com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device

import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.modelhub.DeviceSettingsModel
import com.erabigroupstaffmate.preferences.domain.ErabiPreferences
import com.erabigroupstaffmate.preferences.domain.PreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReadDeviceSettingsUseCase(
    private val parser: Parser,
    private val erabiPreferences: ErabiPreferences,
) {
    operator fun invoke(): Flow<DeviceSettingsModel?> =
        erabiPreferences.readString(PreferencesKey.DeviceSettings)
            .map { json -> json?.let { parser.fromJson<DeviceSettingsModel>(it) } }


}