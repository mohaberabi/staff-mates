package com.erabigroupstaffmate.syncfromserver.data.phase


import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhaseManager
import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.database.dao.ShiftLogDao
import com.erabigroupstaffmate.database.mappers.toEntity
import com.erabigroupstaffmate.network.domain.StaffShiftLogRemoteDataSource

class SyncLogsFromServerPhaseManager(
    private val shiftLogRemoteDataSource: StaffShiftLogRemoteDataSource,
    private val logDao: ShiftLogDao,
) : SyncFromServerPhaseManager {
    override suspend fun syncPhase(payload: PayrollPayload) {
        val logs = shiftLogRemoteDataSource.getAll(payload = payload).map {
            it.toEntity(isSynced = true)
        }
        logDao.upsertAll(logs)
    }
}