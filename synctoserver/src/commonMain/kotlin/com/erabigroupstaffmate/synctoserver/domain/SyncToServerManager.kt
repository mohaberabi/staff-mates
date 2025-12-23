package com.erabigroupstaffmate.synctoserver.domain

interface SyncToServerManager {

    suspend fun syncAllData()
}