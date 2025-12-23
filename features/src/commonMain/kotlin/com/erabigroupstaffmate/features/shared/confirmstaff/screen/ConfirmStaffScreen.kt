package com.erabigroupstaffmate.features.shared.confirmstaff.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.uihub.components.buttons.AppButton
import com.erabigroupstaffmate.uihub.components.body.AppErrorBox
import com.erabigroupstaffmate.uihub.components.design.AppLoader
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.buttons.SecondaryButton
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.components.common.StaffCard
import com.erabigroupstaffmate.features.shared.confirmstaff.viewmodel.ConfirmStaffState
import com.erabigroupstaffmate.features.shared.confirmstaff.viewmodel.ConfirmStaffViewModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.back
import com.erabigroupstaffmate.uihub.resources.confirm
import com.erabigroupstaffmate.uihub.resources.confirm_staff
import com.erabigroupstaffmate.uihub.resources.please_use_the_card_at_your_branch
import com.erabigroupstaffmate.uihub.resources.staff_not_exist
import com.erabigroupstaffmate.uihub.resources.staff_not_in_branch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmStaffScreen(
    onConfirm: (StaffModel) -> Unit,
    onBackClick: () -> Unit,
    viewmodel: ConfirmStaffViewModel = koinViewModel()
) {

    val state by viewmodel.state.collectAsStateWithLifecycle()

    AppScaffold(
        topAppBar = {
            MainAppBar(
                title = stringResource(Res.string.confirm_staff),
                showBackButton = true,
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        ConfirmStaffBody(
            onConfirm = onConfirm,
            onBack = onBackClick,
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        )
    }
}


@Composable
fun ConfirmStaffBody(
    onConfirm: (StaffModel) -> Unit,
    onBack: () -> Unit,
    state: ConfirmStaffState,
    modifier: Modifier = Modifier
) {


    Box(
        modifier = modifier,
    ) {
        when (state) {
            ConfirmStaffState.Loading -> AppLoader()
            ConfirmStaffState.StaffNotAllowedForBranch -> AppErrorBox(
                errorTitle = stringResource(Res.string.staff_not_in_branch),
                errorSubtitle = stringResource(Res.string.please_use_the_card_at_your_branch),
            )

            ConfirmStaffState.StaffNotExist -> AppErrorBox(
                errorTitle = stringResource(Res.string.staff_not_exist),
                errorSubtitle = "Please use a valid erabigroup card or id "
            )

            is ConfirmStaffState.StaffVerified -> Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                StaffCard(
                    staff = state.staff,
                    modifier = Modifier.fillMaxWidth()
                )
                Column {
                    AppButton(
                        onClick = { onConfirm(state.staff) },
                        label = stringResource(Res.string.confirm)
                    )
                    Spacer(Modifier.height(20.dp))
                    SecondaryButton(
                        onClick = { onBack() },
                        label = stringResource(Res.string.back)
                    )
                }
            }
        }
    }

}

