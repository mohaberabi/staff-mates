package com.erabigroupstaffmate.network.data


import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import com.erabigroupstaffmate.network.domain.AuthRemoteDataSource
import com.erabigroupstaffmate.network.domain.UserId
import dev.gitlive.firebase.auth.FirebaseAuth
import kotlinx.coroutines.withContext

class AuthRemoteSourceImpl(
    private val auth: FirebaseAuth,
    private val dispatchers: DispatchersProvider,
) : AuthRemoteDataSource {
    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): UserId = withContext(dispatchers.io) {
        handleFirebaseCall {
            val res = auth.signInWithEmailAndPassword(email = email, password = password)
            res.user?.uid ?: error("User Does Not Exist")
        }
    }


    override suspend fun sendForgetPasswordEmail(
        email: String,
    ) = withContext(dispatchers.io) {
        handleFirebaseCall { auth.sendPasswordResetEmail(email) }
    }

    override suspend fun logout() = withContext(dispatchers.io) {
        handleFirebaseCall { }
    }

}