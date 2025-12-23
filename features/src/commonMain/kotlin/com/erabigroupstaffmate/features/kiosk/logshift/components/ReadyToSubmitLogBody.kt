package com.erabigroupstaffmate.features.kiosk.logshift.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.erabigroupstaffmate.modelhub.ShiftLogModel
import com.erabigroupstaffmate.modelhub.ShiftLogStatus
import com.erabigroupstaffmate.modelhub.mapToStatus
import com.erabigroupstaffmate.uihub.components.common.StaffCard
import com.erabigroupstaffmate.features.kiosk.logshift.viewmodel.LogShiftState

@Composable
fun ReadyToSubmitLogBody(
    logging: Boolean,
    state: LogShiftState.ReadyToSubmit,
    onCheckOut: (ShiftLogModel) -> Unit,
    onCheckIn: (String) -> Unit,
    onConfirm: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        StaffCard(
            staff = state.staff,
        )
        when (state.shift.mapToStatus()) {
            ShiftLogStatus.NeedToCheckIn -> NeedsToCheckInBody(
                businessDate = state.businessDate,
                onCheckIn = onCheckIn,
                logging = logging,
            )

            ShiftLogStatus.NeedToCheckout -> NeedsToLogoutBody(
                shift = state.shift,
                onCheckOut = onCheckOut,
                logging = logging
            )

            ShiftLogStatus.AlreadyCheckedOut -> AlreadyCheckedOutBody(
                shift = state.shift,
                onConfirm = onConfirm
            )
        }
    }
}
