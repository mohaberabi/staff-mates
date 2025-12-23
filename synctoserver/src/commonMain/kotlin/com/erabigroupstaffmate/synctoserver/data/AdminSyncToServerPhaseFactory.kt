package com.erabigroupstaffmate.synctoserver.data


import com.erabigroupstaffmate.synctoserver.data.phase.SyncBorrowingToServerPhaseManager
import com.erabigroupstaffmate.synctoserver.data.phase.SyncDeductToServerPhaseManager
import com.erabigroupstaffmate.synctoserver.data.phase.SyncLogsToServerPhaseManager
import com.erabigroupstaffmate.synctoserver.domain.SyncToServerPhaseFactory
import com.erabigroupstaffmate.synctoserver.domain.SyncToServerPhaseManager

class AdminSyncToServerPhaseFactory(
    private val syncBorrow: SyncBorrowingToServerPhaseManager,
    private val syncDeduct: SyncDeductToServerPhaseManager,
    private val syncLogs: SyncLogsToServerPhaseManager,
) : SyncToServerPhaseFactory {
    private val phases by lazy {
        listOf(
            syncBorrow,
            syncLogs,
            syncDeduct
        )
    }

    override fun create(): List<SyncToServerPhaseManager> = phases
}