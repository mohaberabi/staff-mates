package com.erabigroupstaffmate.core.domain.repository

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.ShiftLogModel
import kotlinx.coroutines.flow.Flow

interface ShiftLogRepository {
    suspend fun getLogsForStaff(
        staffId: String,
        payload: PayrollPayload
    ): List<ShiftLogModel>

    fun getAllByBusinessDay(
        chain: String,
        branch: String,
        day: String,
    ): Flow<List<ShiftLogModel>>

    suspend fun getTotalWorkHrs(
        staffId: String,
        payload: PayrollPayload
    ): Double

    suspend fun getByBusinessDateForStaff(
        staffId: String,
        businessDate: String
    ): ShiftLogModel?

    suspend fun logShift(shift: ShiftLogModel)

    suspend fun getAllNonSynced(): List<ShiftLogModel>

    fun getNonSyncedCount(): Flow<Int>
}


