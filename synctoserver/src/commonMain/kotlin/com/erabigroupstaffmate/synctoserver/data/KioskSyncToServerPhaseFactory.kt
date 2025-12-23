package com.erabigroupstaffmate.synctoserver.data


import com.erabigroupstaffmate.synctoserver.data.phase.SyncLogsToServerPhaseManager
import com.erabigroupstaffmate.synctoserver.domain.SyncToServerPhaseFactory
import com.erabigroupstaffmate.synctoserver.domain.SyncToServerPhaseManager

class KioskSyncToServerPhaseFactory(
    private val syncLogs: SyncLogsToServerPhaseManager,
) : SyncToServerPhaseFactory {
    private val phases by lazy {
        listOf(syncLogs)
    }

    override fun create(): List<SyncToServerPhaseManager> = phases

}