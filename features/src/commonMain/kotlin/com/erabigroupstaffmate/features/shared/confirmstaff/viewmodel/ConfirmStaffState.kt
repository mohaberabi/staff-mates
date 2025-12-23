package com.erabigroupstaffmate.features.shared.confirmstaff.viewmodel

import com.erabigroupstaffmate.modelhub.StaffModel

sealed interface ConfirmStaffState {
    data object Loading : ConfirmStaffState

    data object StaffNotExist : ConfirmStaffState

    data object StaffNotAllowedForBranch : ConfirmStaffState

    data class StaffVerified(val staff: StaffModel) : ConfirmStaffState

}
