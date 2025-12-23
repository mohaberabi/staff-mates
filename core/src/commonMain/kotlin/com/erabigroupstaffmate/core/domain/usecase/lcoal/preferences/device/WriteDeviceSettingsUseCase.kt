package com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device

import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.modelhub.DeviceSettingsModel
import com.erabigroupstaffmate.preferences.domain.ErabiPreferences
import com.erabigroupstaffmate.preferences.domain.PreferencesKey

class WriteDeviceSettingsUseCase(
    private val parser: Parser,
    private val erabiPreferences: ErabiPreferences,
) {
    suspend operator fun invoke(settings: DeviceSettingsModel) {
        val json = parser.toJson(settings)
        erabiPreferences.writeString(PreferencesKey.DeviceSettings, json)
    }
}