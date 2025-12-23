package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.Serializable

@Serializable
data class StaffModel(
    val id: String,
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
    val isActive: Boolean = true
)

fun StaffModel.getAllowanceHrs() = shiftHrs * vacationDays