package com.erabigroupstaffmate.core.data.repository


import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.StaffDeductModel
import com.erabigroupstaffmate.core.domain.repository.StaffDeductRepository
import com.erabigroupstaffmate.database.dao.StaffDeductDao
import com.erabigroupstaffmate.database.mappers.toEntity
import com.erabigroupstaffmate.database.mappers.toModel
import com.erabigroupstaffmate.network.domain.StaffDeductRemoteDataSource
import com.erabigroupstaffmate.utility.constant.DEFAULT_TIMEOUT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withTimeout

class OfflineFirstDeductRepository(
    private val deductDao: StaffDeductDao,
    private val deductRemoteDataSource: StaffDeductRemoteDataSource
) : StaffDeductRepository {
    override suspend fun addDeduct(deduct: StaffDeductModel) {
        runCatching {
            withTimeout(timeMillis = DEFAULT_TIMEOUT) {
                deductRemoteDataSource.addDeduct(deduct)
            }
        }.onSuccess {
            deductDao.upsert(deduct.toEntity(isSynced = true))
        }.onFailure {
            deductDao.upsert(deduct.toEntity(isSynced = false))
        }

    }

    override suspend fun getTotalDeduct(
        staffId: String,
        payload: PayrollPayload
    ): Double = deductDao.getTotalDeduction(
        staffId = staffId,
        month = payload.month,
        chain = payload.chainId,
        branch = payload.branchId,
        year = payload.year
    ) ?: 0.0

    override fun getNonSyncedCount(): Flow<Int> = deductDao.getUnSyncedCount()
    override fun getAllDeductsByYearMonth(
        payload: PayrollPayload,
    ): Flow<List<StaffDeductModel>> = deductDao.getAll(
        year = payload.year,
        month = payload.month,
        chain = payload.chainId,
        branch = payload.branchId
    ).map { list -> list.map { it.toModel() } }

    override fun getAllDeductsByYearMonthForStaff(
        payload: PayrollPayload,
        staffId: String
    ): Flow<List<StaffDeductModel>> = deductDao.getAllForStaff(
        year = payload.year,
        month = payload.month,
        chain = payload.chainId,
        branch = payload.branchId,
        staffId = staffId
    ).map { list -> list.map { it.toModel() } }
}