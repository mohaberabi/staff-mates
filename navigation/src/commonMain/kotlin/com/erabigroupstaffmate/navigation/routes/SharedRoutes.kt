package com.erabigroupstaffmate.navigation.routes

import kotlinx.serialization.Serializable


@Serializable
data object KioskRoute

@Serializable
data object AdminRoute

@Serializable
data object ChooseAppModeRoute

@Serializable
data object LoginRoute

@Serializable
data object HomeRoute


@Serializable
data class WebviewRoute(val url: String)

