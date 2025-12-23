package com.erabigroupstaffmate.core.data.repository


import com.erabigroupstaffmate.core.domain.fts.FullTextSearchManager
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.core.domain.repository.StaffRepository
import com.erabigroupstaffmate.database.dao.StaffDao
import com.erabigroupstaffmate.database.dao.fts.StaffFtsDao
import com.erabigroupstaffmate.database.mappers.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineFirestStaffRepository(
    private val fullTextSearchManager: FullTextSearchManager,
    private val staffFtsDao: StaffFtsDao,
    private val staffDao: StaffDao
) : StaffRepository {
    override suspend fun getById(id: String, chain: String, branch: String): StaffModel? {
        return staffDao.getById(id = id, chain = chain, branch = branch)?.toModel()
    }

    override fun getAllStaff(
        branch: String,
        chain: String
    ): Flow<List<StaffModel>> = staffDao
        .getAll(branch = branch, chain = chain)
        .map { list -> list.map { it.toModel() } }

    override fun searchStaff(
        branch: String,
        chain: String,
        query: String
    ): Flow<List<StaffModel>> = if (query.isBlank()) {
        getAllStaff(branch = branch, chain = chain)
    } else {
        fullTextSearchManager.fullTextSearch(
            query = query,
            ftsResults = { q -> staffFtsDao.searchStaff(q).map { it.toSet() } },
            pivotResults = { ids ->
                staffDao.getAllByIds(ids = ids, chain = chain, branch = branch)
                    .map { list -> list.map { it.toModel() } }
            }
        )
    }
}