package com.erabigroupstaffmate.syncfromserver.data.phase

import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhaseManager
import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.database.dao.StaffDao
import com.erabigroupstaffmate.database.dao.fts.StaffFtsDao
import com.erabigroupstaffmate.database.mappers.toFtsEntity
import com.erabigroupstaffmate.database.mappers.toStaffEntity
import com.erabigroupstaffmate.network.domain.StaffRemoteDataSource
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class SyncStaffFromServerPhaseManager(
    private val staffRemoteDataSource: StaffRemoteDataSource,
    private val staffDao: StaffDao,
    private val staffFtsDao: StaffFtsDao,
) : SyncFromServerPhaseManager {
    override suspend fun syncPhase(payload: PayrollPayload) {
        val remote = staffRemoteDataSource.getAllStaff(
            branch = payload.branchId,
            chain = payload.chainId
        )
        staffFtsDao.deleteAll()
        supervisorScope {
            val staffJob = launch {
                staffDao.upsertAll(remote.map { it.toStaffEntity() })
            }
            val ftsJob = launch {
                staffFtsDao.insertAll(remote.map { it.toFtsEntity() })
            }
            joinAll(
                staffJob,
                ftsJob,
            )
        }

    }
}