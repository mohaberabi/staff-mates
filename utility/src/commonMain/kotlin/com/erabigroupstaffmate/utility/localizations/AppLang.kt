package com.erabigroupstaffmate.utility.localizations

import androidx.compose.ui.unit.LayoutDirection
import kotlinx.serialization.Serializable


@Serializable
enum class AppLang(
    val code: String,
    val label: String,
) {
    Arabic("ar", "العربيه"),
    English("en", "English")
}

fun AppLang.layoutDirection() = when (this) {
    AppLang.Arabic -> LayoutDirection.Rtl
    AppLang.English -> LayoutDirection.Ltr
}