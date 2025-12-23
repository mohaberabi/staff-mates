package com.erabigroup.erabigroupstaffmate.app.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.ime
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.erabigroupstaffmate.uihub.components.body.AppScaffold
import com.erabigroupstaffmate.uihub.components.compose.EventCollector
import com.erabigroupstaffmate.uihub.components.snackbar.PrimarySnackBarHost
import com.erabigroupstaffmate.navigation.graph.MainNavGraph
import com.erabigroupstaffmate.uihub.designsystem.LocalSnackBarController


@Composable
fun AppEntryPoint(
    startRoute: Any,
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val snackBarController = LocalSnackBarController.current
    EventCollector(
        snackBarController.collect(),
    ) { snackbarMessage ->
        snackBarHostState.showSnackbar(snackbarMessage)
    }

    AppScaffold(
        modifier = Modifier
            .fillMaxSize()
            .consumeWindowInsets(WindowInsets.ime),
        snackbarHost = {
            PrimarySnackBarHost(hostState = snackBarHostState)
        }
    ) {
        MainNavGraph(
            startDestination = startRoute,
        )
    }
}

