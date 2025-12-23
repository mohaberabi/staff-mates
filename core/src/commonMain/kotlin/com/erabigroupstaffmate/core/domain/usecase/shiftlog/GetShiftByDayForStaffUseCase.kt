package com.erabigroupstaffmate.core.domain.usecase.shiftlog

import com.erabigroupstaffmate.core.domain.repository.ShiftLogRepository

class GetShiftByDayForStaffUseCase(
    private val shiftLogRepository: ShiftLogRepository
) {


    suspend operator fun invoke(
        staffId: String,
        businessDate: String,
    ) = shiftLogRepository.getByBusinessDateForStaff(
        staffId = staffId,
        businessDate = businessDate
    )
}