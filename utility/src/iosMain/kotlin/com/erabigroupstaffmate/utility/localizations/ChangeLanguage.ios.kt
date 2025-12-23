package com.erabigroupstaffmate.utility.localizations

import com.erabigroupstaffmate.utility.localizations.AppLang
import platform.Foundation.NSUserDefaults

actual fun changeLanguage(lang: AppLang) {
    NSUserDefaults.standardUserDefaults.setObject(
        arrayListOf(lang.code), "AppleLanguages"
    )
}