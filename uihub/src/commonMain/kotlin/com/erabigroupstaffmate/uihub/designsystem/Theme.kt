package com.erabigroupstaffmate.uihub.designsystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import com.erabigroupstaffmate.uihub.components.getAsyncImageLoader
import com.erabigroupstaffmate.uihub.components.snackbar.provideSnackBarController
import com.erabigroupstaffmate.utility.localizations.AppLang
import com.erabigroupstaffmate.utility.localizations.layoutDirection


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ErabigroupStaffMateTheme(
    language: AppLang = AppLang.English,
    content: @Composable () -> Unit
) {
    setSingletonImageLoaderFactory {
        getAsyncImageLoader(it)
    }
    CompositionLocalProvider(
        LocalAppLanguage provides language,
        LocalLayoutDirection provides language.layoutDirection(),
        LocalSnackBarController provides provideSnackBarController(),
    ) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            typography = Typography,
            content = content
        )
    }

}
