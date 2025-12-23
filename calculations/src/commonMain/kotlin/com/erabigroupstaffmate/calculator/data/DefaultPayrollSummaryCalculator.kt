package com.erabigroupstaffmate.calculator.data


import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.PayrollSummaryModel
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.core.domain.repository.BorrowingRepository
import com.erabigroupstaffmate.core.domain.repository.ShiftLogRepository
import com.erabigroupstaffmate.core.domain.repository.StaffDeductRepository
import com.erabigroupstaffmate.calculator.domain.PayrollSummaryCalculator
import com.erabigroupstaffmate.calculator.domain.StaffCalculator
import com.erabigroupstaffmate.modelhub.getAllowanceHrs
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope

class DefaultPayrollSummaryCalculator(
    private val staffCalculator: StaffCalculator,
    private val shiftLogRepository: ShiftLogRepository,
    private val deductRepository: StaffDeductRepository,
    private val borrowingRepository: BorrowingRepository
) : PayrollSummaryCalculator {
    override suspend fun getPayrollSummary(
        staff: StaffModel,
        payload: PayrollPayload
    ): PayrollSummaryModel = supervisorScope {

        val totalWorkHrDeferred = async {
            shiftLogRepository.getTotalWorkHrs(staffId = staff.id, payload = payload)
        }

        val deductDeferred = async {
            deductRepository.getTotalDeduct(payload = payload, staffId = staff.id)
        }

        val borrowingDeferred = async {
            borrowingRepository.getTotalBorrowing(staffId = staff.id, payload = payload)
        }

        val earnPerHr = staffCalculator.getEarnPerHour(
            baseSalary = staff.baseSalary,
            shiftHrs = staff.shiftHrs
        )

        val (borrowing, deduct, totalWorkHrs) = Triple(
            first = borrowingDeferred.await(),
            second = deductDeferred.await(),
            third = totalWorkHrDeferred.await()
        )

        val netSalary = staffCalculator.getNetSalaryForMonth(
            staff = staff,
            totalWorkHrs = totalWorkHrs,
            ttlBorrow = borrowing.toInt(),
            ttlDeduct = deduct.toInt()
        )
        val growthSalary = staffCalculator.getGrowthSalaryForMonth(
            staff = staff,
            totalWorkHrs = totalWorkHrs,
        )
        val ttlWorkHrsWithAllowance = staffCalculator.getTotalWorkHrsWithAllowance(
            totalWorkHrs = totalWorkHrs,
            allowanceHrs = staff.getAllowanceHrs()
        )
        PayrollSummaryModel(
            staff = staff,
            totalBorrowings = borrowing,
            totalDeductions = deduct,
            netSalary = netSalary,
            growthSalary = growthSalary,
            ttlWorkHrs = totalWorkHrs,
            ttlWorkHrsWithAllowance = ttlWorkHrsWithAllowance,
            earnPerHr = earnPerHr,
        )
    }
}