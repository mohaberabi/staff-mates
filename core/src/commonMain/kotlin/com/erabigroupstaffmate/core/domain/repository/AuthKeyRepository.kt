package com.erabigroupstaffmate.core.domain.repository

import com.erabigroupstaffmate.modelhub.AuthKeyModel

interface AuthKeyRepository {
    suspend fun getAuthKey(key: String): AuthKeyModel?
}