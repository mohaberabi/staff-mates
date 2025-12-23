package com.erabigroupstaffmate.core.data

import com.erabigroupstaffmate.core.domain.AuthEventController
import com.erabigroupstaffmate.modelhub.AuthRole
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class DefaultAuthEventController : AuthEventController {


    private val responseChannel = Channel<AuthRole>()

    private val requestChannel = Channel<AuthRole>()

    override fun collectAuthResponses(): Flow<AuthRole> = responseChannel.receiveAsFlow()

    override suspend fun respond(key: AuthRole) {
        responseChannel.send(key)
    }

    override suspend fun request(key: AuthRole) {

        requestChannel.send(key)
    }

    override fun collectRequests(): Flow<AuthRole> = requestChannel.receiveAsFlow()
}