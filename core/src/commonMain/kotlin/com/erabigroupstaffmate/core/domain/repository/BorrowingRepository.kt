package com.erabigroupstaffmate.core.domain.repository

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.StaffBorrowModel
import com.erabigroupstaffmate.modelhub.StaffDeductModel
import kotlinx.coroutines.flow.Flow

interface BorrowingRepository {
    suspend fun getTotalBorrowing(
        staffId: String,
        payload: PayrollPayload,
    ): Double

    fun getNonSyncedCount(): Flow<Int>
    suspend fun addBorrow(borrow: StaffBorrowModel)

    fun getAllBorrowByYearMonth(
        payload: PayrollPayload,
    ): Flow<List<StaffBorrowModel>>

    fun getAllBorrowByYearMonthForStaff(
        payload: PayrollPayload,
        staffId: String,
    ): Flow<List<StaffBorrowModel>>
}