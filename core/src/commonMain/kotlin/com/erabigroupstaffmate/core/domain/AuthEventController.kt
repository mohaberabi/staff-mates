package com.erabigroupstaffmate.core.domain

import com.erabigroupstaffmate.modelhub.AuthRole
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

interface AuthEventController {
    fun collectAuthResponses(): Flow<AuthRole>

    fun collectRequests(): Flow<AuthRole>
    suspend fun respond(key: AuthRole)
    suspend fun request(key: AuthRole)
}


