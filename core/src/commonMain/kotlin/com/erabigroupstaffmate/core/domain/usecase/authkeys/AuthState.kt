package com.erabigroupstaffmate.core.domain.usecase.authkeys

import com.erabigroupstaffmate.utility.constant.AUTH_LENGTH


enum class AuthStatus {
    Initial,
    NonAuthed,
    Authed,
}

data class AuthState(

    val isLoading: Boolean = false,
    val code: String = "",
    val status: AuthStatus = AuthStatus.Initial
)

fun AuthState.canAuth() = code.length == AUTH_LENGTH
