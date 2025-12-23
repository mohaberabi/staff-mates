package com.erabigroupstaffmate.core.domain.usecase.shiftlog

import com.erabigroupstaffmate.core.domain.factory.ShiftLogFactory
import com.erabigroupstaffmate.modelhub.ShiftLogModel
import com.erabigroupstaffmate.core.domain.repository.ShiftLogRepository


typealias TotalWorkedHours = Double

class CheckStaffOutUseCase(
    private val shiftLogFactory: ShiftLogFactory,
    private val shiftLogRepository: ShiftLogRepository,
) {

    suspend operator fun invoke(previous: ShiftLogModel): TotalWorkedHours {
        val shift = shiftLogFactory.createForCheckout(previous)
        shiftLogRepository.logShift(shift = shift)
        return shift.totalWorkedHours
    }
}