package com.erabigroupstaffmate.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("staff")
data class StaffEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val legalName: String,
    val profilePicUrl: String,
    val frontIdUrl: String,
    val backIdUrl: String,
    val fullName: String,
    val title: String,
    val branchId: String,
    val chainId: String,
    val branchName: String,
    val chainName: String,
    val vacationDays: Int,
    val baseSalary: Double,
    val shiftHrs: Int,
    val joinDate: String,
    val isActive: Boolean = true,
    val staffPhone: String = id
)
