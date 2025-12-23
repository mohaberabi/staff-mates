package com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.locale

import com.erabigroupstaffmate.parser.Parser

import com.erabigroupstaffmate.preferences.domain.ErabiPreferences
import com.erabigroupstaffmate.preferences.domain.PreferencesKey
import com.erabigroupstaffmate.utility.localizations.AppLang

class WriteAppLanguageUseCase(
    private val parser: Parser,
    private val erabiPreferences: ErabiPreferences
) {


    suspend operator fun invoke(lang: AppLang) {
        erabiPreferences.writeString(PreferencesKey.AppLocale, parser.toJson(lang))
    }
}