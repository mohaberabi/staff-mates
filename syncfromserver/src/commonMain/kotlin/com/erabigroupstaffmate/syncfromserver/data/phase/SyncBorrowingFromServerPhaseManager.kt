package com.erabigroupstaffmate.syncfromserver.data.phase


import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhaseManager
import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.database.dao.StaffBorrowDao
import com.erabigroupstaffmate.database.mappers.toEntity
import com.erabigroupstaffmate.network.domain.StaffBorrowRemoteDataSource

class SyncBorrowingFromServerPhaseManager(
    private val borrowRemoteDataSource: StaffBorrowRemoteDataSource,
    private val staffBorrowDao: StaffBorrowDao
) : SyncFromServerPhaseManager {
    override suspend fun syncPhase(payload: PayrollPayload) {
        val borrow = borrowRemoteDataSource.getAllByYearMonth(
            payload = payload
        ).map { it.toEntity(isSynced = true) }
        staffBorrowDao.upsertAll(borrow)
    }
}