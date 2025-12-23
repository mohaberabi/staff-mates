package com.erabigroupstaffmate.core.data.repository

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.ShiftLogModel
import com.erabigroupstaffmate.core.domain.repository.ShiftLogRepository
import com.erabigroupstaffmate.database.mappers.toModel
import com.erabigroupstaffmate.database.dao.ShiftLogDao
import com.erabigroupstaffmate.database.mappers.toEntity
import com.erabigroupstaffmate.network.domain.StaffShiftLogRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withTimeout

class OfflineFirstShiftLogRepository(
    private val shiftLogDao: ShiftLogDao,
    private val shiftLogRemoteDataSource: StaffShiftLogRemoteDataSource,
) : ShiftLogRepository {
    override suspend fun getLogsForStaff(
        staffId: String,
        payload: PayrollPayload
    ): List<ShiftLogModel> {
        return listOf()
    }

    override fun getAllByBusinessDay(
        chain: String,
        branch: String,
        day: String
    ): Flow<List<ShiftLogModel>> =
        shiftLogDao.getAllByBusinessDay(day = day, chain = chain, branch = branch)
            .map { list -> list.map { it.toModel() } }

    override suspend fun getTotalWorkHrs(
        staffId: String,
        payload: PayrollPayload
    ): Double = shiftLogDao.getTotalWorkHours(
        staffId = staffId,
        year = payload.year,
        chain = payload.chainId,
        branch = payload.branchId,
        month = payload.month,
    ) ?: 0.0

    override suspend fun getByBusinessDateForStaff(
        staffId: String,
        businessDate: String
    ): ShiftLogModel? = shiftLogDao.getByBusinessDay(
        day = businessDate,
        staffId = staffId
    )?.toModel()

    override suspend fun logShift(shift: ShiftLogModel) {
        runCatching {
            withTimeout(timeMillis = 5000L) { shiftLogRemoteDataSource.addLog(shift) }
        }.onSuccess {
            shiftLogDao.upsert(shift.toEntity(isSynced = true))
        }.onFailure {
            shiftLogDao.upsert(shift.toEntity(isSynced = false))
            it.printStackTrace()
        }
    }

    override suspend fun getAllNonSynced(): List<ShiftLogModel> {
        return shiftLogDao.getNonSynced().map { it.toModel() }
    }

    override fun getNonSyncedCount(): Flow<Int> {
        return shiftLogDao.getUnSyncedCount()
    }
}