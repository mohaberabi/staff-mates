package com.erabigroupstaffmate.features.shared.login.viewmodel

import org.jetbrains.compose.resources.StringResource


sealed interface LoginEvents {
    data object LoggedIn : LoginEvents
    data class Error(val error: StringResource) : LoginEvents
}