package com.erabigroupstaffmate.features.admin.adddeduct.screen


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.uihub.uimodel.BorrowReason
import com.erabigroupstaffmate.uihub.uimodel.DeductReason
import com.erabigroupstaffmate.modelhub.StaffDeductType
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.compose.EventCollector
import com.erabigroupstaffmate.uihub.components.body.FocuseAwareCompose
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.components.snackbar.SnackbarMessage
import com.erabigroupstaffmate.uihub.designsystem.LocalSnackBarController
import com.erabigroupstaffmate.features.admin.adddeduct.viewmodel.AddDeductEvent
import com.erabigroupstaffmate.features.admin.adddeduct.viewmodel.AddDeductState
import com.erabigroupstaffmate.features.admin.adddeduct.viewmodel.AddDeductViewModel
import com.erabigroupstaffmate.features.admin.adddeduct.viewmodel.canAdd
import com.erabigroupstaffmate.features.admin.deductions.components.AddDeductScreenBody
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.add_borrow
import com.erabigroupstaffmate.uihub.resources.add_deduct
import com.erabigroupstaffmate.uihub.resources.something_went_wrong
import com.erabigroupstaffmate.utility.constant.deductionsMultiplier
import com.erabigroupstaffmate.uihub.utils.extensions.compose.modifier.imeInsetsPadding
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDeductScreen(
    onBackClick: () -> Unit,
    viewModel: AddDeductViewModel = koinViewModel()
) {
    val snackBarController = LocalSnackBarController.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    EventCollector(
        flow = viewModel.messages,
    ) { message ->
        when (message) {
            AddDeductEvent.DeductSaved -> onBackClick()
            AddDeductEvent.ErrorSavingDeduct -> {
                snackBarController.send(
                    SnackbarMessage.Error(
                        stringResource = Res.string.something_went_wrong
                    )
                )
            }
        }
    }

    AppScaffold(
        topAppBar = {
            MainAppBar(
                onBackClick = onBackClick,
                title = stringResource(state.buttonLabel()),
                showBackButton = true
            )
        }
    ) { padding ->

        FocuseAwareCompose(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            AddDeductScreenBody(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .imeInsetsPadding(),
                onConfirm = { viewModel.commitDeductToStaff() },
                onReasonChanged = { viewModel.reasonChanged(it) },
                onAmountChanged = { viewModel.amountChanged(it) },
                canSave = state.canAdd(),
                isSaving = state.loading,
                amounts = deductionsMultiplier,
                reasons = state.deductReasons(),
                selectedAmount = state.amount,
                selectedReason = state.reason,
                buttonLabel = stringResource(state.buttonLabel()),
                staff = state.staff,
                earnPerDay = state.earnPerDay
            )
        }
    }
}

private fun AddDeductState.deductReasons() = when (type) {
    StaffDeductType.Deduct -> DeductReason.entries.map { it.stringRes }
    StaffDeductType.Borrow -> BorrowReason.entries.map { it.stringRes }
}

private fun AddDeductState.buttonLabel() = when (type) {
    StaffDeductType.Deduct -> Res.string.add_deduct
    StaffDeductType.Borrow -> Res.string.add_borrow
}

