package com.erabigroupstaffmate.features.shared.syncer.fromserver.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.uihub.components.buttons.AppButton
import com.erabigroupstaffmate.uihub.components.sheets.AppDatePicker
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.compose.EventCollector
import com.erabigroupstaffmate.uihub.components.dialogs.FullScreenLoaderDialog
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.components.design.SimpleListItem
import com.erabigroupstaffmate.uihub.designsystem.LocalSnackBarController
import com.erabigroupstaffmate.features.shared.syncer.fromserver.viewmodel.SyncFromServerActions
import com.erabigroupstaffmate.features.shared.syncer.fromserver.viewmodel.SyncFromServerState
import com.erabigroupstaffmate.features.shared.syncer.fromserver.viewmodel.SyncFromServerViewModel
import com.erabigroupstaffmate.uihub.components.date.DatePickerWheelSheet
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.change_date
import com.erabigroupstaffmate.uihub.resources.record_date
import com.erabigroupstaffmate.uihub.resources.sync_data
import com.erabigroupstaffmate.uihub.resources.sync_from_server
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyncFromServerScreen(
    onBackClick: () -> Unit,
    viewModel: SyncFromServerViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val snackBarController = LocalSnackBarController.current
    EventCollector(
        viewModel.messages,
    ) { message ->
        snackBarController.send(message)
    }

    AppScaffold(
        topAppBar = {
            MainAppBar(
                showBackButton = true,
                title = stringResource(Res.string.sync_from_server),
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        SyncFromServerBody(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(16.dp),
            state = state,
            onActions = viewModel::onAction
        )
    }
    if (state.isSyncing) {
        FullScreenLoaderDialog()
    }

}


@Composable
fun SyncFromServerBody(
    modifier: Modifier = Modifier,
    state: SyncFromServerState,
    onActions: (SyncFromServerActions) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        SimpleListItem(
            leading = stringResource(Res.string.record_date),
            trailing = "${state.selectedMonth.monthNumber} - ${state.selectedYear}"
        )

        AppButton(
            onClick = { onActions(SyncFromServerActions.StartSync) },
            label = stringResource(Res.string.sync_data)
        )
        AppButton(
            onClick = { onActions(SyncFromServerActions.ToggleDatePicker) },
            label = stringResource(Res.string.change_date)
        )

    }
    if (state.showDatePicker) {
        DatePickerWheelSheet(
            onDismiss = {
                onActions(
                    SyncFromServerActions.ToggleDatePicker
                )

            },
            onConfirm = { year, monthState ->
                onActions(
                    SyncFromServerActions.DateChanged(
                        selectedYear = year,
                        selectedMonth = monthState
                    )
                )
            },
        )
    }
}