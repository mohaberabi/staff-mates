package com.erabigroupstaffmate.calculator.data

import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.utility.constant.DAY_IN_MONTH
import com.erabigroupstaffmate.calculator.domain.StaffCalculator
import com.erabigroupstaffmate.modelhub.getAllowanceHrs


class DefaultStaffCalculator : StaffCalculator {
    override fun getNetSalaryForMonth(
        staff: StaffModel,
        totalWorkHrs: Double,
        ttlBorrow: Int,
        ttlDeduct: Int,
    ): Double {
        val growth = getGrowthSalaryForMonth(staff = staff, totalWorkHrs = totalWorkHrs)
        val totalDeducted = ttlDeduct + ttlBorrow
        return growth - totalDeducted
    }

    override fun getTotalWorkHrsWithAllowance(
        totalWorkHrs: Double,
        allowanceHrs: Int
    ): Double = if (totalWorkHrs <= 0) 0.0 else {
        totalWorkHrs + allowanceHrs
    }

    override fun getGrowthSalaryForMonth(
        staff: StaffModel,
        totalWorkHrs: Double
    ): Double {
        val allowance = staff.getAllowanceHrs()
        val ttlHrsWithAllowance = getTotalWorkHrsWithAllowance(
            totalWorkHrs = totalWorkHrs,
            allowanceHrs = allowance
        )
        val earnPerHr = getEarnPerHour(baseSalary = staff.baseSalary, shiftHrs = staff.shiftHrs)
        return ttlHrsWithAllowance * earnPerHr
    }

    override fun getEarnPerHour(
        baseSalary: Double,
        shiftHrs: Int,
    ): Double {
        val earnPerDay = baseSalary / DAY_IN_MONTH
        return earnPerDay / shiftHrs
    }

    override fun getEarnPerDay(
        shiftHrs: Int,
        baseSalary: Double
    ): Double {
        if (shiftHrs <= 0 || baseSalary <= 0.0) return 0.0
        return baseSalary / DAY_IN_MONTH
    }
}