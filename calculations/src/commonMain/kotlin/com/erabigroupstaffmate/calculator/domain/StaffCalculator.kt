package com.erabigroupstaffmate.calculator.domain

import com.erabigroupstaffmate.modelhub.StaffModel

interface StaffCalculator {

    fun getEarnPerHour(
        baseSalary: Double,
        shiftHrs: Int,
    ): Double

    fun getGrowthSalaryForMonth(
        staff: StaffModel,
        totalWorkHrs: Double,
    ): Double

    fun getNetSalaryForMonth(
        staff: StaffModel,
        totalWorkHrs: Double,
        ttlBorrow: Int,
        ttlDeduct: Int,
    ): Double

    fun getTotalWorkHrsWithAllowance(
        totalWorkHrs: Double,
        allowanceHrs: Int
    ): Double

    fun getEarnPerDay(
        shiftHrs: Int,
        baseSalary: Double,
    ): Double
}