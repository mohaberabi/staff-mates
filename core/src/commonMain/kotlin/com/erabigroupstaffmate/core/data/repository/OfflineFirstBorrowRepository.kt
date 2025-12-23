package com.erabigroupstaffmate.core.data.repository

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.StaffBorrowModel
import com.erabigroupstaffmate.core.domain.repository.BorrowingRepository
import com.erabigroupstaffmate.database.dao.StaffBorrowDao
import com.erabigroupstaffmate.database.mappers.toEntity
import com.erabigroupstaffmate.database.mappers.toModel
import com.erabigroupstaffmate.modelhub.StaffDeductModel
import com.erabigroupstaffmate.network.domain.StaffBorrowRemoteDataSource
import com.erabigroupstaffmate.utility.constant.DEFAULT_TIMEOUT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withTimeout

class OfflineFirstBorrowRepository(
    private val borrowDao: StaffBorrowDao,
    private val borrowRemoteDataSource: StaffBorrowRemoteDataSource,
) : BorrowingRepository {
    override suspend fun getTotalBorrowing(
        staffId: String,
        payload: PayrollPayload
    ): Double = borrowDao.getTotalBorrowing(
        staffId = staffId,
        month = payload.month,
        chain = payload.chainId,
        branch = payload.branchId,
        year = payload.year
    ) ?: 0.0

    override fun getNonSyncedCount(): Flow<Int> = borrowDao.getUnSyncedCount()

    override suspend fun addBorrow(borrow: StaffBorrowModel) {
        runCatching {
            withTimeout(timeMillis = DEFAULT_TIMEOUT) {
                borrowRemoteDataSource.addBorrow(borrow = borrow)
            }
        }.onSuccess {
            borrowDao.upsert(borrow = borrow.toEntity(isSynced = true))
        }.onFailure {
            borrowDao.upsert(borrow.toEntity(isSynced = false))
        }

    }

    override fun getAllBorrowByYearMonth(payload: PayrollPayload): Flow<List<StaffBorrowModel>> {
        return borrowDao.getAll(
            year = payload.year,
            month = payload.month,
            chain = payload.chainId,
            branch = payload.branchId
        ).map { list -> list.map { it.toModel() } }
    }

    override fun getAllBorrowByYearMonthForStaff(
        payload: PayrollPayload,
        staffId: String
    ): Flow<List<StaffBorrowModel>> = borrowDao.getAllForStaff(
        year = payload.year,
        month = payload.month,
        chain = payload.chainId,
        branch = payload.branchId,
        staffId = staffId
    ).map { list -> list.map { it.toModel() } }
}