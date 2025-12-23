package com.erabigroupstaffmate.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.erabigroupstaffmate.database.entity.StaffEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface StaffDao {

    @Upsert
    suspend fun upsertAll(staff: List<StaffEntity>)

    @Query("SELECT * FROM staff WHERE chainId=:chain AND branchId=:branch")

    fun getAll(branch: String, chain: String): Flow<List<StaffEntity>>


    @Query("SELECT * FROM staff WHERE chainId=:chain AND branchId=:branch AND id =:id")

    suspend fun getById(branch: String, chain: String, id: String): StaffEntity?

    @Query("SELECT * FROM staff WHERE chainId=:chain AND branchId=:branch AND id IN (:ids)")
    fun getAllByIds(
        ids: Set<String>,
        chain: String,
        branch: String,
    ): Flow<List<StaffEntity>>
}