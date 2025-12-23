package com.erabigroupstaffmate.features.kiosk.logshift.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.modelhub.ShiftLogModel
import com.erabigroupstaffmate.uihub.components.body.AppErrorBox
import com.erabigroupstaffmate.uihub.components.design.AppLoader
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.compose.EventCollector
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.features.kiosk.logshift.components.ReadyToSubmitLogBody
import com.erabigroupstaffmate.features.kiosk.logshift.viewmodel.LogShiftEvents
import com.erabigroupstaffmate.features.kiosk.logshift.viewmodel.LogShiftState
import com.erabigroupstaffmate.features.kiosk.logshift.viewmodel.LogShiftViewModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.attendance
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogShiftScreen(
    onLogEvent: (LogShiftEvents) -> Unit,
    viewModel: LogShiftViewModel = koinViewModel(),
    onBackClick: () -> Unit,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val logging by viewModel.isLogging.collectAsStateWithLifecycle()

    EventCollector(
        viewModel.events,
    ) { event -> onLogEvent(event) }

    AppScaffold(
        topAppBar = {
            MainAppBar(
                title = stringResource(Res.string.attendance),
                showBackButton = true,
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        LogShiftBody(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            state = state,
            logging = logging,
            onConfirm = onBackClick,
            onCheckout = { viewModel.checkOut(it) },
            onCheckIn = { viewModel.checkIn(it) }
        )
    }
}


@Composable
fun LogShiftBody(
    modifier: Modifier = Modifier,
    state: LogShiftState,
    logging: Boolean,
    onConfirm: () -> Unit,
    onCheckout: (ShiftLogModel) -> Unit,
    onCheckIn: (String) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (state) {
            LogShiftState.Error -> AppErrorBox()
            LogShiftState.Loading -> AppLoader()
            LogShiftState.NotInBusinessHours -> AppErrorBox()
            is LogShiftState.ReadyToSubmit -> ReadyToSubmitLogBody(
                state = state,
                onConfirm = onConfirm,
                onCheckOut = onCheckout,
                logging = logging,
                onCheckIn = onCheckIn
            )
        }
    }
}


