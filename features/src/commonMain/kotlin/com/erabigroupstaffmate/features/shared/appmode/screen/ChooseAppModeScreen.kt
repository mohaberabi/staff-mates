package com.erabigroupstaffmate.features.shared.appmode.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.erabigroupstaffmate.features.di.adminFeaturesModule
import com.erabigroupstaffmate.features.di.adminModule
import com.erabigroupstaffmate.features.di.kioskModule
import com.erabigroupstaffmate.features.shared.appmode.viewmodel.AppModeEvents
import com.erabigroupstaffmate.features.shared.appmode.viewmodel.AppModeViewModel
import com.erabigroupstaffmate.modelhub.AppMode
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.buttons.AppButton
import com.erabigroupstaffmate.uihub.components.buttons.AppRadioButton
import com.erabigroupstaffmate.uihub.components.compose.EventCollector
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.components.design.PrimaryDivider
import com.erabigroupstaffmate.uihub.components.dialogs.FullScreenLoaderDialog
import com.erabigroupstaffmate.uihub.components.snackbar.SnackbarMessage
import com.erabigroupstaffmate.uihub.designsystem.LocalSnackBarController
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.confirm
import com.erabigroupstaffmate.uihub.resources.logo
import com.erabigroupstaffmate.uihub.resources.please_select_app_mode
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.context.loadKoinModules


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseAppModeScreen(
    viewModel: AppModeViewModel = koinViewModel(),
    onSynced: (mode: AppMode) -> Unit,
) {

    val snackBarController = LocalSnackBarController.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    EventCollector(
        viewModel.events,
    ) { event ->
        when (event) {
            is AppModeEvents.Error -> snackBarController
                .send(SnackbarMessage.Error(stringResource = event.message))

            AppModeEvents.SyncedData -> onSynced(state.selectedMode)
        }
    }
    AppScaffold(
        topAppBar = {
            MainAppBar(
                showBackButton = false,
                titleContent = {
                    Image(
                        imageResource(Res.drawable.logo),
                        "",
                        modifier = Modifier.size(85.dp)
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "App mode",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "How do you want to use this device ... ?",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Gray,
            )
            Spacer(Modifier.height(12.dp))
            PrimaryDivider()
            AppMode.entries.filter { !it.isUnknown() }.fastForEach { mode ->
                SelectAppModeCard(
                    mode = mode,
                    onSelect = { viewModel.selectMode(mode) },
                    selected = state.selectedMode == mode
                )
            }
            AppButton(
                onClick = { viewModel.confirm() },
                label = stringResource(Res.string.confirm),
                enabled = state.selectedMode != AppMode.Unknown
            )
        }

        if (state.isSyncing) {
            FullScreenLoaderDialog()
        }
    }

}

fun loadAppModulesAtRuntime(
    mode: AppMode?,
) = when (mode) {
    AppMode.Kiosk -> loadKoinModules(listOf(kioskModule))
    AppMode.Admin -> loadKoinModules(listOf(adminModule, adminFeaturesModule))
    else -> Unit
}