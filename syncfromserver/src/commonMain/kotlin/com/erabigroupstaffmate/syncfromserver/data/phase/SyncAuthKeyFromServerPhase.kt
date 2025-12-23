package com.erabigroupstaffmate.syncfromserver.data.phase


import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhaseManager
import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.database.dao.AuthKeyDao
import com.erabigroupstaffmate.database.mappers.toEntity
import com.erabigroupstaffmate.network.domain.AuthKeysRemoteDataSource

class SyncAuthKeyFromServerPhase(
    private val authKeyDao: AuthKeyDao,
    private val authRemoteDataSource: AuthKeysRemoteDataSource,
) : SyncFromServerPhaseManager {

    override suspend fun syncPhase(payload: PayrollPayload) {

        val remote = authRemoteDataSource.getAllForBranch(
            branch = payload.branchId,
            chain = payload.chainId
        ).map { it.toEntity() }

        authKeyDao.upsertAll(remote)
    }
}