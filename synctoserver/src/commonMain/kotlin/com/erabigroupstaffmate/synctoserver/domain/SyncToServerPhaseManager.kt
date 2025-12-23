package com.erabigroupstaffmate.synctoserver.domain

interface SyncToServerPhaseManager {
    suspend fun syncPhase()
}