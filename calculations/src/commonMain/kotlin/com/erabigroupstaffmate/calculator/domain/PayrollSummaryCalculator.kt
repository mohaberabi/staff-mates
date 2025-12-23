package com.erabigroupstaffmate.calculator.domain

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.PayrollSummaryModel
import com.erabigroupstaffmate.modelhub.StaffModel

interface PayrollSummaryCalculator {
    suspend fun getPayrollSummary(
        staff: StaffModel,
        payload: PayrollPayload
    ): PayrollSummaryModel
}