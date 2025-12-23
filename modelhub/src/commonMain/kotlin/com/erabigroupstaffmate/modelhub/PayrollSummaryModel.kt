package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.Serializable

@Serializable
data class PayrollSummaryModel(
    val staff: StaffModel,
    val totalDeductions: Double,
    val totalBorrowings: Double,
    val netSalary: Double,
    val growthSalary: Double,
    val ttlWorkHrs: Double,
    val ttlWorkHrsWithAllowance: Double,
    val earnPerHr: Double,
)
