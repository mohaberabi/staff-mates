package com.erabigroupstaffmate.network.domain

import com.erabigroupstaffmate.modelhub.StaffModel

interface StaffRemoteDataSource {


    suspend fun getAllStaff(
        branch: String,
        chain: String,
    ): List<StaffModel>
}