package com.erabigroupstaffmate.navigation.compose

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.erabigroupstaffmate.features.shared.confirmstaff.screen.ConfirmStaffScreen
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.navigation.ConfirmStaffRoute


internal fun NavGraphBuilder.confirmStaff(
    onBack: () -> Unit,
    onConfirm: (StaffModel) -> Unit,
) = composable<ConfirmStaffRoute> {
    ConfirmStaffScreen(
        onBackClick = onBack,
        onConfirm = onConfirm
    )
}