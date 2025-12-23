package com.erabigroupstaffmate.core.domain.repository

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.StaffDeductModel
import kotlinx.coroutines.flow.Flow


interface StaffDeductRepository {


    suspend fun addDeduct(deduct: StaffDeductModel)
    suspend fun getTotalDeduct(
        staffId: String,
        payload: PayrollPayload,
    ): Double

    fun getNonSyncedCount(): Flow<Int>
    fun getAllDeductsByYearMonth(
        payload: PayrollPayload,
    ): Flow<List<StaffDeductModel>>

    fun getAllDeductsByYearMonthForStaff(
        payload: PayrollPayload,
        staffId: String,
    ): Flow<List<StaffDeductModel>>
}