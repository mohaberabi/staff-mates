package com.erabigroupstaffmate.features.shared.syncer.toserver.screen

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
import com.erabigroupstaffmate.modelhub.UnSyncedCounter
import com.erabigroupstaffmate.modelhub.canSync
import com.erabigroupstaffmate.uihub.components.buttons.AppButton
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.compose.EventCollector
import com.erabigroupstaffmate.uihub.components.dialogs.FullScreenLoaderDialog
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.uihub.components.design.SimpleListItem
import com.erabigroupstaffmate.uihub.designsystem.LocalSnackBarController
import com.erabigroupstaffmate.features.shared.syncer.toserver.viewmodel.SyncToServerViewModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.sync_data
import com.erabigroupstaffmate.uihub.resources.sync_to_server
import com.erabigroupstaffmate.uihub.resources.unsync_borrow
import com.erabigroupstaffmate.uihub.resources.unsync_deduct
import com.erabigroupstaffmate.uihub.resources.unsync_logs

import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyncToServerScreen(
    onBackClick: () -> Unit,
    viewModel: SyncToServerViewModel = koinViewModel()
) {
    val isSyncing by viewModel.isSyncing.collectAsStateWithLifecycle()
    val unSyncedCounter by viewModel.unSyncedCounter.collectAsStateWithLifecycle()

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
                title = stringResource(Res.string.sync_to_server),
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        SyncFromServerBody(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(16.dp),
            unSyncedCounter = unSyncedCounter,
            onStartSync = viewModel::startSync
        )
    }
    if (isSyncing) {
        FullScreenLoaderDialog()
    }

}


@Composable
fun SyncFromServerBody(
    modifier: Modifier = Modifier,
    onStartSync: () -> Unit,
    unSyncedCounter: UnSyncedCounter,
) {
    Column(
        modifier = modifier
    ) {
        SimpleListItem(
            leading = stringResource(Res.string.unsync_logs),
            trailing = unSyncedCounter.unSyncedLogs.toString()
        )
        SimpleListItem(
            leading = stringResource(Res.string.unsync_deduct),
            trailing = unSyncedCounter.unSyncedDeduct.toString()
        )
        SimpleListItem(
            leading = stringResource(Res.string.unsync_borrow),
            trailing = unSyncedCounter.unSyncedBorrow.toString()
        )

        AppButton(
            enabled = unSyncedCounter.canSync(),
            onClick = { onStartSync() },
            label = stringResource(Res.string.sync_data)
        )
    }

}