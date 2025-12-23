package com.erabigroupstaffmate.features.shared.login.viewmodel

data class LoginState(
    val email: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val chain: String = "",
    val branch: String = "",
) {
    val canLogin
        get() = email.trim().isNotBlank() &&
                password.trimStart().length == password.length
                && password.isNotBlank()
                && chain.isNotBlank()
                && branch.isNotBlank()
}
