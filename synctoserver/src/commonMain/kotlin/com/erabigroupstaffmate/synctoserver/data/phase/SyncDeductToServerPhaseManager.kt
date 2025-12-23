package com.erabigroupstaffmate.synctoserver.data.phase


import com.erabigroupstaffmate.database.dao.StaffDeductDao
import com.erabigroupstaffmate.database.mappers.toModel
import com.erabigroupstaffmate.network.domain.StaffDeductRemoteDataSource
import com.erabigroupstaffmate.synctoserver.domain.SyncToServerPhaseManager

class SyncDeductToServerPhaseManager(
    private val deductRemoteDataSource: StaffDeductRemoteDataSource,
    private val deductDao: StaffDeductDao,
) : SyncToServerPhaseManager {
    override suspend fun syncPhase() {
        val nonSynced = deductDao.getAllNonSynced()
        deductRemoteDataSource.addAll(nonSynced.map { it.toModel() })
        val markedSynced = nonSynced.map { it.copy(isSynced = true) }
        deductDao.upsertAll(deduct = markedSynced)
    }
}