package com.erabigroupstaffmate.network.domain

import com.erabigroupstaffmate.modelhub.AuthKeyModel

interface AuthKeysRemoteDataSource {


    suspend fun getAllForBranch(
        branch: String,
        chain: String
    ): List<AuthKeyModel>
}