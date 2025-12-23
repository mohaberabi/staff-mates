package com.erabigroupstaffmate.uihub.designsystem

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color


val ThinGray = Color.LightGray.copy(alpha = 0.35f)

private val PrimaryBlack = Color(0xFF000000)
private val SurfaceGrey = Color(0xFFF5F5F5)
val ErrorRed = Color(0xFFD32F2F)
val SuccessGreen = Color(0xFF388E3C)
val BackgroundWhite = Color(0xFFFFFFFF)
val LightColorScheme = lightColorScheme(
    primary = PrimaryBlack,
    onPrimary = BackgroundWhite,
    secondary = SurfaceGrey,
    onSecondary = PrimaryBlack,
    background = BackgroundWhite,
    onBackground = PrimaryBlack,
    surface = SurfaceGrey,
    onSurface = PrimaryBlack,
    error = ErrorRed,
    onError = BackgroundWhite
)