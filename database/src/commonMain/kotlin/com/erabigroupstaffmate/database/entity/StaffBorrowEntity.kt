package com.erabigroupstaffmate.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("borrow")
data class StaffBorrowEntity(
    @PrimaryKey(false) val id: String,
    val borrowAtMillis: Long,
    val staffId: String,
    val staffName: String,
    val amount: Double,
    val reason: String,
    val recordYear: String,
    val recordMonth: String,
    val branch: String,
    val chain: String,
    val isSynced: Boolean = false,
)