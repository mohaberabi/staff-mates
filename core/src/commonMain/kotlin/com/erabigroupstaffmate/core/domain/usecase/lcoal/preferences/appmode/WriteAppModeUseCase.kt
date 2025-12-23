package com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.appmode

import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.modelhub.AppMode
import com.erabigroupstaffmate.preferences.domain.ErabiPreferences
import com.erabigroupstaffmate.preferences.domain.PreferencesKey

class WriteAppModeUseCase(
    private val parser: Parser,
    private val preferences: ErabiPreferences
) {
    suspend operator fun invoke(mode: AppMode) {
        val json = parser.toJson(mode)
        preferences.writeString(PreferencesKey.AppMode, json)
    }
}