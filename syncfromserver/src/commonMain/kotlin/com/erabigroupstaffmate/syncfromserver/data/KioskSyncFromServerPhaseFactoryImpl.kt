package com.erabigroupstaffmate.syncfromserver.data

import com.erabigroupstaffmate.syncfromserver.data.phase.SyncAuthKeyFromServerPhase
import com.erabigroupstaffmate.syncfromserver.data.phase.SyncLogsFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.data.phase.SyncStaffFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhasesFactory

class KioskSyncFromServerPhaseFactoryImpl(
    private val syncLogsPhaseManager: SyncLogsFromServerPhaseManager,
    private val syncStaffPhaseManager: SyncStaffFromServerPhaseManager,
    private val syncAuthKeys: SyncAuthKeyFromServerPhase,
) : SyncFromServerPhasesFactory {
    private val phases by lazy {
        listOf(
            syncLogsPhaseManager,
            syncStaffPhaseManager,
            syncAuthKeys
        )
    }

    override fun create(): List<SyncFromServerPhaseManager> = phases

}