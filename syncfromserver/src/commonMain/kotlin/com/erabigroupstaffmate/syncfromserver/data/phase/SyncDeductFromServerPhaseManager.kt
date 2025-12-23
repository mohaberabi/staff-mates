package com.erabigroupstaffmate.syncfromserver.data.phase


import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhaseManager
import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.database.dao.StaffDeductDao
import com.erabigroupstaffmate.database.mappers.toEntity
import com.erabigroupstaffmate.network.domain.StaffDeductRemoteDataSource

class SyncDeductFromServerPhaseManager(
    private val deductRemoteDataSource: StaffDeductRemoteDataSource,
    private val deductDao: StaffDeductDao,
) : SyncFromServerPhaseManager {
    override suspend fun syncPhase(payload: PayrollPayload) {
        val deduct = deductRemoteDataSource.getAllByYearMonth(
            payload = payload
        ).map { it.toEntity(isSynced = true) }
        deductDao.upsertAll(deduct)
    }
}