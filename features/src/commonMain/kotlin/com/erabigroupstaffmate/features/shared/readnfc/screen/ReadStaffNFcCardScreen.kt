package com.erabigroupstaffmate.features.shared.readnfc.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.erabigroupstaffmate.nfc.domain.NfcManager
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.compose.EventCollector
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.nfc.rememberNfcManager
import com.erabigroupstaffmate.uihub.components.snackbar.SnackbarMessage
import com.erabigroupstaffmate.uihub.designsystem.LocalSnackBarController
import com.erabigroupstaffmate.features.shared.readnfc.viewmodel.ReadStaffNfcEvents
import com.erabigroupstaffmate.features.shared.readnfc.viewmodel.ReadStaffNfcViewModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.ic_contactless
import com.erabigroupstaffmate.uihub.resources.read_nfc
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReadStaffNfcCardScreen(
    onBackClick: () -> Unit,
    onReadDone: (staffId: String) -> Unit = {},
    manager: NfcManager = rememberNfcManager(),
    viewModel: ReadStaffNfcViewModel = koinViewModel(
        parameters = { parametersOf(manager) }
    ),
) {
    val snackBarController = LocalSnackBarController.current
    EventCollector(viewModel.events) { event ->
        when (event) {
            is ReadStaffNfcEvents.CardRead -> onReadDone(event.staffId)
            is ReadStaffNfcEvents.ErrorReadingCard -> snackBarController.send(
                SnackbarMessage.Error(
                    message = event.message
                )
            )
        }
    }
    AppScaffold(
        topAppBar = {
            MainAppBar(
                showBackButton = true,
                onBackClick = onBackClick,
                title = stringResource(Res.string.read_nfc)
            )
        }
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                "Please place your card near to the device to read the data ",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Icon(
                vectorResource(Res.drawable.ic_contactless),
                "",
                tint = Color.Gray,
                modifier = Modifier.size(100.dp)
            )

        }
    }

}