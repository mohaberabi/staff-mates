package com.erabigroupstaffmate.utility.localizations

import com.erabigroupstaffmate.utility.localizations.AppLang
import java.util.Locale

actual fun changeLanguage(lang: AppLang) {
    Locale.setDefault(Locale(lang.code))
}