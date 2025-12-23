package com.erabigroupstaffmate.features.kiosk.logshift.viewmodel

import com.erabigroupstaffmate.modelhub.ShiftLogModel
import com.erabigroupstaffmate.modelhub.StaffModel


sealed interface LogShiftState {
    data object Loading : LogShiftState

    data object Error : LogShiftState

    data object NotInBusinessHours : LogShiftState

    data class ReadyToSubmit(
        val businessDate: String,
        val staff: StaffModel,
        val shift: ShiftLogModel?,
    ) : LogShiftState
}

