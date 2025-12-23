package com.erabigroupstaffmate.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("auth")
data class AuthKeyEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String,
    val isAdmin: Boolean = false,
    val roles: String,
)
