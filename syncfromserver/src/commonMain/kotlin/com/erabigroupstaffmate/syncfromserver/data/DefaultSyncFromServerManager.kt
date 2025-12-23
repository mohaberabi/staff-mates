package com.erabigroupstaffmate.syncfromserver.data

import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerManager
import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhasesFactory
import com.erabigroupstaffmate.modelhub.PayrollPayload
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class DefaultSyncFromServerManager(
    private val phaseFactory: SyncFromServerPhasesFactory,
) : SyncFromServerManager {
    override suspend fun syncAllData(payload: PayrollPayload) = supervisorScope {
        phaseFactory.create().map {
            launch { it.syncPhase(payload) }
        }.joinAll()
    }
}