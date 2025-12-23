package com.erabigroupstaffmate.network.domain

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.StaffBorrowModel

interface StaffBorrowRemoteDataSource {


    suspend fun addBorrow(borrow: StaffBorrowModel)
    suspend fun addAll(borrows: List<StaffBorrowModel>)

    suspend fun getAllByYearMonth(
        payload: PayrollPayload,
    ): List<StaffBorrowModel>
}