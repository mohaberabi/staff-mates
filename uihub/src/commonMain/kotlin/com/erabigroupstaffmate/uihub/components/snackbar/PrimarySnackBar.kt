package com.erabigroupstaffmate.uihub.components.snackbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun PrimarySnackBarHost(
    modifier: Modifier = Modifier,
    hostState: SnackbarHostState,
) {

    SnackbarHost(
        modifier = modifier
            .padding(bottom = 60.dp)
            .padding(8.dp),
        hostState = hostState,
        snackbar = { data ->
            PrimarySnackBar(
                dismissAction = {},
                snackbarMessage = data.visuals as SnackbarMessage
            )
        }
    )
}

@Composable
private fun PrimarySnackBar(
    snackbarMessage: SnackbarMessage,
    dismissAction: () -> Unit = {}
) {

    Snackbar(
        containerColor = snackbarMessage.containerColor,
        action = {
            snackbarMessage.action?.let {
                TextButton(onClick = it) {
                    snackbarMessage.actionLabel?.let { actLabl ->
                        Text(actLabl)
                    }
                }
            }
        },
        dismissAction = { dismissAction.invoke() },
    ) {
        Text(
            snackbarMessage.getString(),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}