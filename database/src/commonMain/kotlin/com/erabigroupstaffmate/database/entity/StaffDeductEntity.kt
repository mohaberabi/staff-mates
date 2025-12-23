package com.erabigroupstaffmate.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("staff_deduct")
data class StaffDeductEntity(

    @PrimaryKey(autoGenerate = false) val id: String,
    val staffId: String,
    val deductAtMillis: Long,
    val amount: Double,
    val reason: String,
    val recordYear: String,
    val recordMonth: String,
    val branch: String,
    val chain: String,
    val isSynced: Boolean
)
