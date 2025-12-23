package com.erabigroupstaffmate.network.domain

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.ShiftLogModel

interface StaffShiftLogRemoteDataSource {

    suspend fun addLog(log: ShiftLogModel)
    suspend fun addAll(logs: List<ShiftLogModel>)
    suspend fun getAll(
        payload: PayrollPayload,
    ): List<ShiftLogModel>

}