package com.erabigroupstaffmate.synctoserver.data.phase


import com.erabigroupstaffmate.database.dao.ShiftLogDao
import com.erabigroupstaffmate.database.mappers.toModel
import com.erabigroupstaffmate.network.domain.StaffShiftLogRemoteDataSource
import com.erabigroupstaffmate.synctoserver.domain.SyncToServerPhaseManager

class SyncLogsToServerPhaseManager(
    private val shiftLogRemoteDataSource: StaffShiftLogRemoteDataSource,
    private val logDao: ShiftLogDao
) : SyncToServerPhaseManager {
    override suspend fun syncPhase() {
        val nonSynced = logDao.getNonSynced()
        shiftLogRemoteDataSource.addAll(nonSynced.map { it.toModel() })
        val markedSynced = nonSynced.map { it.copy(isSynced = true) }
        logDao.upsertAll(logs = markedSynced)
    }
}