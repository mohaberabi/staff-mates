package com.erabigroupstaffmate.synctoserver.data


import com.erabigroupstaffmate.synctoserver.domain.SyncToServerManager
import com.erabigroupstaffmate.synctoserver.domain.SyncToServerPhaseFactory
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class DefaultSyncToServerManager(
    private val syncToServerPhaseFactory: SyncToServerPhaseFactory
) : SyncToServerManager {
    override suspend fun syncAllData() = supervisorScope {
        val phases = syncToServerPhaseFactory.create()
        phases.map {
            launch { it.syncPhase() }
        }.joinAll()
    }
}