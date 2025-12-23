package com.erabigroupstaffmate.features.admin.setup.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.nfc.domain.NfcManager
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.compose.EventCollector
import com.erabigroupstaffmate.uihub.components.common.numpad.SimpleNumPad
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.nfc.rememberNfcManager
import com.erabigroupstaffmate.uihub.components.snackbar.SnackbarMessage
import com.erabigroupstaffmate.uihub.designsystem.LocalSnackBarController
import com.erabigroupstaffmate.features.admin.setup.viewmodel.writenfc.WriteStaffToNfcActions
import com.erabigroupstaffmate.features.admin.setup.viewmodel.writenfc.WriteStaffToNfcEvents
import com.erabigroupstaffmate.features.admin.setup.viewmodel.writenfc.WriteStaffToNfcViewModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.add_staff_card
import com.erabigroupstaffmate.uihub.resources.place_nfc_when_ready
import com.erabigroupstaffmate.uihub.resources.reading_the_card_error
import com.erabigroupstaffmate.uihub.resources.saving_data_to_the_card_error
import com.erabigroupstaffmate.uihub.resources.staff_data_was_saved
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WriteStaffToNfcScreen(
    onBackClick: () -> Unit,
    nfcManager: NfcManager = rememberNfcManager(),
    viewModel: WriteStaffToNfcViewModel = koinViewModel(
        parameters = {
            parametersOf(
                nfcManager
            )
        }
    )
) {
    val snackBarController = LocalSnackBarController.current
    EventCollector(viewModel.events) { event ->
        val message = when (event) {
            WriteStaffToNfcEvents.ErrorReading -> SnackbarMessage.Error(message = event.toMessage())
            WriteStaffToNfcEvents.ErrorWriting -> SnackbarMessage.Error(message = event.toMessage())
            WriteStaffToNfcEvents.WritingDataDone -> SnackbarMessage.Done(message = event.toMessage())
        }
        snackBarController.send(message)
    }
    AppScaffold(
        topAppBar = {
            MainAppBar(
                showBackButton = true,
                onBackClick = onBackClick,
                title = stringResource(Res.string.add_staff_card),
            )
        }
    ) { padding ->
        WriteStaffToNfcBody(
            modifier = Modifier.padding(padding),
            onAction = viewModel::onAction
        )
    }
}

private suspend fun WriteStaffToNfcEvents.toMessage(): String {
    return when (this) {
        WriteStaffToNfcEvents.ErrorReading -> getString(Res.string.reading_the_card_error)
        WriteStaffToNfcEvents.ErrorWriting -> getString(Res.string.saving_data_to_the_card_error)
        WriteStaffToNfcEvents.WritingDataDone -> getString(Res.string.staff_data_was_saved)
    }
}

@Composable
private fun WriteStaffToNfcBody(
    modifier: Modifier = Modifier,
    onAction: (WriteStaffToNfcActions) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            stringResource(Res.string.place_nfc_when_ready),
            style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(8.dp))

        SimpleNumPad(
            size = 11,
            onSubmit = {
                onAction(WriteStaffToNfcActions.StaffIdChanged(it))
                    .also { onAction(WriteStaffToNfcActions.StartReading) }
            }
        )

    }
}
