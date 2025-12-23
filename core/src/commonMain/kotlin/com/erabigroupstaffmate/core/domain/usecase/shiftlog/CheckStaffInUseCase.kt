package com.erabigroupstaffmate.core.domain.usecase.shiftlog

import com.erabigroupstaffmate.core.domain.factory.ShiftLogFactory
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.core.domain.repository.ShiftLogRepository

class CheckStaffInUseCase(
    private val shiftLogFactory: ShiftLogFactory,
    private val shiftLogRepository: ShiftLogRepository,
) {


    suspend operator fun invoke(
        staff: StaffModel,
        businessDate: String,
    ) {
        val shift = shiftLogFactory.createCheckIn(
            staff = staff,
            businessDate = businessDate,
        )
        shiftLogRepository.logShift(shift = shift)
    }
}