package com.erabigroupstaffmate.network.domain

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.StaffDeductModel

interface StaffDeductRemoteDataSource {


    suspend fun addDeduct(deduct: StaffDeductModel)
    suspend fun addAll(deducts: List<StaffDeductModel>)
    suspend fun getAllByYearMonth(
        payload: PayrollPayload,
    ): List<StaffDeductModel>

}