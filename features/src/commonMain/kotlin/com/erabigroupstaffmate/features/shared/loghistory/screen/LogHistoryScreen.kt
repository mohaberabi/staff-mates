package com.erabigroupstaffmate.features.shared.loghistory.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.features.admin.deductions.components.LogHistoryListItem
import com.erabigroupstaffmate.modelhub.uidmodel.ShiftLogUiModel
import com.erabigroupstaffmate.uihub.components.body.AppPlaceHolder
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.common.DateChangerRow
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.components.sheets.AppDatePicker
import com.erabigroupstaffmate.features.shared.loghistory.viewmodel.LogHistoryViewModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.change_date
import com.erabigroupstaffmate.uihub.resources.no_logs_for_date
import com.erabigroupstaffmate.uihub.resources.no_logs_for_date_subttl
import com.erabigroupstaffmate.uihub.resources.shift_logs
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogHistoryScreen(
    viewModel: LogHistoryViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {
    val logs by viewModel.logs.collectAsStateWithLifecycle()
    val selectedDate by viewModel.selectedDate.collectAsStateWithLifecycle()
    val showDatePicker by viewModel.showDatePicker.collectAsStateWithLifecycle()
    AppScaffold(
        topAppBar = {
            MainAppBar(
                title = stringResource(Res.string.shift_logs),
                onBackClick = onBackClick,
                showBackButton = true
            )
        }
    ) {

        LogHistoryScreenBody(
            modifier = Modifier.fillMaxSize().padding(it).padding(16.dp),
            selectedDate = selectedDate,
            onToggleDate = viewModel::toggleDatePicker,
            logs = logs
        )
    }
    if (showDatePicker) {
        AppDatePicker(
            onDismiss = { viewModel.toggleDatePicker() },
            onSelected = { viewModel.dateChanged(it) }
        )
    }
}

@Composable
fun LogHistoryScreenBody(
    modifier: Modifier = Modifier,
    selectedDate: String,
    onToggleDate: () -> Unit,
    logs: List<ShiftLogUiModel>,
) {

    if (logs.isNotEmpty()) {
        LazyColumn(
            modifier = modifier,
        ) {
            stickyHeader {
                DateChangerRow(
                    selectedDate = selectedDate,
                    onToggleDate = onToggleDate
                )
            }
            items(logs) { log ->
                LogHistoryListItem(log = log)
            }
        }

    } else {
        AppPlaceHolder(
            retryLabel = stringResource(Res.string.change_date),
            title = stringResource(Res.string.no_logs_for_date),
            subtitle = stringResource(Res.string.no_logs_for_date_subttl),
            onRetry = { onToggleDate() }
        )
    }


}