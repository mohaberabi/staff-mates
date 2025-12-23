package com.erabigroupstaffmate.database.entity

import androidx.room.Entity
import androidx.room.Fts4


@Fts4(contentEntity = StaffEntity::class)
@Entity("staff_fts")
data class StaffFtsEntity(
    val fullName: String,
    val title: String,
    val id: String
)
