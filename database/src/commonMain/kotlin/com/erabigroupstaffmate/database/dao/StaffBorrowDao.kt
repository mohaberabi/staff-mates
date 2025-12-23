package com.erabigroupstaffmate.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.erabigroupstaffmate.database.entity.StaffBorrowEntity
import com.erabigroupstaffmate.database.entity.StaffDeductEntity
import com.erabigroupstaffmate.modelhub.StaffBorrowModel
import kotlinx.coroutines.flow.Flow


@Dao
interface StaffBorrowDao {
    @Query(
        """
        SELECT * FROM borrow WHERE recordMonth=:month
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
    ): Flow<List<StaffBorrowEntity>>

    @Upsert
    suspend fun upsert(borrow: StaffBorrowEntity)

    @Upsert
    suspend fun upsertAll(borrow: List<StaffBorrowEntity>)

    @Query(
        """
        SELECT * FROM borrow WHERE recordMonth=:month
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
    ): Flow<List<StaffBorrowEntity>>

    @Query(
        """
        SELECT SUM(amount) 
        FROM borrow
        WHERE staffId = :staffId 
          AND recordYear = :year 
          AND recordMonth = :month 
          AND branch = :branch 
          AND chain = :chain
        """
    )
    suspend fun getTotalBorrowing(
        staffId: String,
        year: String,
        month: String,
        branch: String,
        chain: String
    ): Double?


    @Query("SELECT * FROM borrow WHERE isSynced IS FALSE ")
    suspend fun getAllNonSynced(): List<StaffBorrowEntity>

    @Query("SELECT COUNT(*) FROM borrow WHERE isSynced IS FALSE ")
    fun getUnSyncedCount(): Flow<Int>


}

