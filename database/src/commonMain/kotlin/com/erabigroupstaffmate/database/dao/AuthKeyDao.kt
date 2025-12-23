package com.erabigroupstaffmate.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.erabigroupstaffmate.database.entity.AuthKeyEntity


@Dao
interface AuthKeyDao {


    @Upsert
    suspend fun upsertAll(keys: List<AuthKeyEntity>)


    @Query("SELECT * FROM auth WHERE id =:code")
    suspend fun getByCode(code: String): AuthKeyEntity?
}