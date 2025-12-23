package com.erabigroupstaffmate.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.erabigroupstaffmate.database.entity.ShiftLogEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ShiftLogDao {

    @Upsert
    suspend fun upsert(log: ShiftLogEntity)


    @Upsert
    suspend fun upsertAll(logs: List<ShiftLogEntity>)


    @Query(
        """
        SELECT * FROM shift_log WHERE businessDate=:day
        AND branch=:branch
        AND chain=:chain
        """
    )
    fun getAllByBusinessDay(
        day: String,
        branch: String,
        chain: String
    ): Flow<List<ShiftLogEntity>>

    @Query(
        """
        SELECT SUM(totalWorkedHours) 
        FROM shift_log
        WHERE staffId = :staffId 
          AND logYear = :year 
          AND logMonth = :month 
          AND branch = :branch 
          AND chain = :chain
        """
    )
    suspend fun getTotalWorkHours(
        staffId: String,
        year: String,
        month: String,
        branch: String,
        chain: String
    ): Double?

    @Query("SELECT * FROM shift_log WHERE staffId=:staffId AND businessDate=:day")

    suspend fun getByBusinessDay(day: String, staffId: String): ShiftLogEntity?

    @Query("SELECT * FROM shift_log WHERE isSynced IS FALSE ")
    suspend fun getNonSynced(): List<ShiftLogEntity>

    @Query("SELECT COUNT(*) FROM shift_log WHERE isSynced IS FALSE ")
    fun getUnSyncedCount(): Flow<Int>
}