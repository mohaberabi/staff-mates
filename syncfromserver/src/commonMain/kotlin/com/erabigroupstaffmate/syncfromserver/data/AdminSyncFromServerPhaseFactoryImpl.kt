package com.erabigroupstaffmate.syncfromserver.data

import com.erabigroupstaffmate.syncfromserver.data.phase.SyncAuthKeyFromServerPhase
import com.erabigroupstaffmate.syncfromserver.data.phase.SyncBorrowingFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.data.phase.SyncDeductFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.data.phase.SyncLogsFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.data.phase.SyncStaffFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhasesFactory

class AdminSyncFromServerPhaseFactoryImpl(
    private val syncBorrowingPhaseManager: SyncBorrowingFromServerPhaseManager,
    private val syncDeductPhaseManager: SyncDeductFromServerPhaseManager,
    private val syncLogsPhaseManager: SyncLogsFromServerPhaseManager,
    private val syncStaffPhaseManager: SyncStaffFromServerPhaseManager,
    private val syncAuthKeys: SyncAuthKeyFromServerPhase,
) : SyncFromServerPhasesFactory {
    private val phases by lazy {
        listOf(
            syncBorrowingPhaseManager,
            syncDeductPhaseManager,
            syncLogsPhaseManager,
            syncStaffPhaseManager,
            syncAuthKeys,
        )
    }

    override fun create(): List<SyncFromServerPhaseManager> = phases
}