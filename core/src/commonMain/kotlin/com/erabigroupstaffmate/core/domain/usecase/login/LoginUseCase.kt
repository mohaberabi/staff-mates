package com.erabigroupstaffmate.core.domain.usecase.login

import com.erabigroupstaffmate.network.domain.AuthRemoteDataSource

class LoginUseCase(
    private val authRemoteDataSource: AuthRemoteDataSource
) {


    suspend operator fun invoke(
        email: String,
        password: String,
    ): String = authRemoteDataSource.loginWithEmailAndPassword(
        email = email,
        password = password
    )
}