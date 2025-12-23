package com.erabigroupstaffmate.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.erabigroupstaffmate.database.entity.StaffDeductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StaffDeductDao {
    @Query(
        """
        SELECT * FROM staff_deduct WHERE recordMonth=:month
        AND recordYear=:year
        AND branch=:branch
        AND chain=:chain
        AND staffId=:staffId
        """
    )
    fun getAllForStaff(
        year: String,
        month: String,
        branch: String,
        chain: String,
        staffId: String,
    ): Flow<List<StaffDeductEntity>>

    @Upsert
    suspend fun upsert(deduct: StaffDeductEntity)

    @Upsert
    suspend fun upsertAll(deduct: List<StaffDeductEntity>)

    @Query(
        """
        SELECT * FROM staff_deduct WHERE recordMonth=:month
        AND recordYear=:year
        AND branch=:branch
        AND chain=:chain
        """
    )
    fun getAll(
        year: String,
        month: String,
        branch: String,
        chain: String
    ): Flow<List<StaffDeductEntity>>

    @Query(
        """
        SELECT SUM(amount) 
        FROM staff_deduct
        WHERE staffId = :staffId 
          AND recordYear = :year 
          AND recordMonth = :month 
          AND branch = :branch 
          AND chain = :chain
        """
    )
    suspend fun getTotalDeduction(
        staffId: String,
        year: String,
        month: String,
        branch: String,
        chain: String
    ): Double?

    @Query("SELECT * FROM staff_deduct WHERE isSynced IS FALSE ")
    suspend fun getAllNonSynced(): List<StaffDeductEntity>


    @Query("SELECT COUNT(*) FROM staff_deduct WHERE isSynced IS FALSE ")
    fun getUnSyncedCount(): Flow<Int>
}