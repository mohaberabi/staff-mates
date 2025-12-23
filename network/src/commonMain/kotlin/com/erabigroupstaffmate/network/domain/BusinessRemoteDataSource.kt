package com.erabigroupstaffmate.network.domain

import com.erabigroupstaffmate.modelhub.WorkingHoursModel

interface BusinessRemoteDataSource {
    suspend fun getWorkingHours(
        branch: String,
        chain: String
    ): WorkingHoursModel
}