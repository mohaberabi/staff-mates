package com.erabigroupstaffmate.database.dao.fts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.erabigroupstaffmate.database.entity.StaffFtsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StaffFtsDao {
    @Query("SELECT id FROM staff_fts WHERE staff_fts MATCH :query")
    fun searchStaff(
        query: String,
    ): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(fts: List<StaffFtsEntity>)

    @Query("DELETE FROM staff_fts")
    suspend fun deleteAll()

}