package com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.locale

import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.preferences.domain.ErabiPreferences
import com.erabigroupstaffmate.preferences.domain.PreferencesKey
import com.erabigroupstaffmate.utility.localizations.AppLang
import kotlinx.coroutines.flow.map

class ReadAppLanguageUseCase(
    private val erabiPreferences: ErabiPreferences,
    private val parser: Parser,
) {


    operator fun invoke() = erabiPreferences.readString(PreferencesKey.AppLocale).map { json ->
        json?.let { parser.fromJson<AppLang>(it) } ?: AppLang.English
    }
}