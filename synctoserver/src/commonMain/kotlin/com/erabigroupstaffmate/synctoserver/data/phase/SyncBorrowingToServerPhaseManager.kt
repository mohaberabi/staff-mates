package com.erabigroupstaffmate.synctoserver.data.phase


import com.erabigroupstaffmate.database.dao.StaffBorrowDao
import com.erabigroupstaffmate.database.mappers.toModel
import com.erabigroupstaffmate.network.domain.StaffBorrowRemoteDataSource
import com.erabigroupstaffmate.synctoserver.domain.SyncToServerPhaseManager

class SyncBorrowingToServerPhaseManager(
    private val borrowRemoteDataSource: StaffBorrowRemoteDataSource,
    private val staffBorrowDao: StaffBorrowDao
) : SyncToServerPhaseManager {
    override suspend fun syncPhase() {
        val nonSynced = staffBorrowDao.getAllNonSynced()
        borrowRemoteDataSource.addAll(nonSynced.map { it.toModel() })
        val markedSynced = nonSynced.map { it.copy(isSynced = true) }
        staffBorrowDao.upsertAll(markedSynced)
    }
}