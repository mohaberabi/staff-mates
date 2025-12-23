package com.erabigroupstaffmate.network.domain


typealias UserId = String

interface AuthRemoteDataSource {

    suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): UserId


    suspend fun sendForgetPasswordEmail(
        email: String
    )

    suspend fun logout()
}