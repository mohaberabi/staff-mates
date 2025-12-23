package com.erabigroupstaffmate.uihub.designsystem

import androidx.compose.runtime.staticCompositionLocalOf
import com.erabigroupstaffmate.uihub.components.snackbar.provideSnackBarController
import com.erabigroupstaffmate.utility.localizations.AppLang


val LocalSnackBarController = staticCompositionLocalOf { provideSnackBarController() }
val LocalAppLanguage = staticCompositionLocalOf {
    AppLang.English
}
