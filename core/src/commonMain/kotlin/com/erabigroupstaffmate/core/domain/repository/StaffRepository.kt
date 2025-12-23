package com.erabigroupstaffmate.core.domain.repository

import com.erabigroupstaffmate.modelhub.StaffModel
import kotlinx.coroutines.flow.Flow

interface StaffRepository {


    suspend fun getById(id: String, chain: String, branch: String): StaffModel?
    fun getAllStaff(
        branch: String,
        chain: String
    ): Flow<List<StaffModel>>

    fun searchStaff(
        branch: String,
        chain: String,
        query: String,
    ): Flow<List<StaffModel>>
}