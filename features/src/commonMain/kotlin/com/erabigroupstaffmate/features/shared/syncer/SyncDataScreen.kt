package com.erabigroupstaffmate.features.shared.syncer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.design.MainAppBar
import com.erabigroupstaffmate.features.shared.settings.components.SettingsLitItem
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.sync_data
import com.erabigroupstaffmate.uihub.resources.sync_from_server
import com.erabigroupstaffmate.uihub.resources.sync_to_server
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyncDataScreen(
    onSyncFromServer: () -> Unit,
    onSyncToServer: () -> Unit,
    onGoBack: () -> Unit,
) {
    AppScaffold(
        topAppBar = {
            MainAppBar(
                title = stringResource(Res.string.sync_data),
                onBackClick = onGoBack,
                showBackButton = true
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            SettingsLitItem(
                leading = Res.string.sync_from_server,
                onClick = onSyncFromServer,
            )
            SettingsLitItem(
                leading = Res.string.sync_to_server,
                onClick = onSyncToServer,
            )

        }
    }
}