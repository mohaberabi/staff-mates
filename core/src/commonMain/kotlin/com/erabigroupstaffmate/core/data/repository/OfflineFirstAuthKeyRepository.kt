package com.erabigroupstaffmate.core.data.repository


import com.erabigroupstaffmate.modelhub.AuthKeyModel
import com.erabigroupstaffmate.core.domain.repository.AuthKeyRepository
import com.erabigroupstaffmate.database.dao.AuthKeyDao
import com.erabigroupstaffmate.database.mappers.toModel

class OfflineFirstAuthKeyRepository(
    private val authKeyDao: AuthKeyDao,
) : AuthKeyRepository {
    override suspend fun getAuthKey(key: String): AuthKeyModel? {
        return authKeyDao.getByCode(code = key)?.toModel()
    }


}