package com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.appmode

import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.modelhub.AppMode
import com.erabigroupstaffmate.preferences.domain.ErabiPreferences
import com.erabigroupstaffmate.preferences.domain.PreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReadAppModeUseCase(
    private val parser: Parser,
    private val preferences: ErabiPreferences
) {
    operator fun invoke(): Flow<AppMode?> = preferences
        .readString(PreferencesKey.AppMode)
        .map { parser.fromJson(it) }
}