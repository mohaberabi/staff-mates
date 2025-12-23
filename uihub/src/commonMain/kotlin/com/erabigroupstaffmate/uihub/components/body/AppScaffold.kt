package com.erabigroupstaffmate.uihub.components.body

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topAppBar: @Composable () -> Unit = {},
    bottomAppBar: @Composable () -> Unit = {},
    fab: @Composable () -> Unit = {},
    snackbarHost: (@Composable () -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit,
) {

    Scaffold(
        bottomBar = bottomAppBar,
        snackbarHost = {
            snackbarHost?.invoke()
        },
        modifier = modifier,
        topBar = topAppBar,
        floatingActionButton = fab,
        floatingActionButtonPosition = FabPosition.Center,
    ) { padding ->
        content(padding)
    }
}
