package com.erabigroupstaffmate.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("shift_log")
data class ShiftLogEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val staffId: String,
    val staffFullName: String,
    val logInMillis: Long,
    val logOutMillis: Long? = null,
    val totalWorkedHours: Double = 0.0,
    val businessDate: String,
    val logMonth: String,
    val logYear: String,
    val chain: String,
    val branch: String,
    val isSynced: Boolean = false
)
