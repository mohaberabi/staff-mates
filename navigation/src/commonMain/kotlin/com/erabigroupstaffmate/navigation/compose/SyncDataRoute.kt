package com.erabigroupstaffmate.navigation.compose

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.erabigroupstaffmate.features.shared.syncer.SyncDataScreen
import com.erabigroupstaffmate.features.shared.syncer.fromserver.screen.SyncFromServerScreen
import com.erabigroupstaffmate.features.shared.syncer.toserver.screen.SyncToServerScreen
import kotlinx.serialization.Serializable

@Serializable
internal data object SyncDataRoute

@Serializable
internal data object SyncToServerRoute

@Serializable
internal data object SyncFromServerRoute

internal fun NavGraphBuilder.syncData(
    navController: NavHostController
) = composable<SyncDataRoute> {
    SyncDataScreen(
        onSyncToServer = navController::goSyncToServer,
        onSyncFromServer = navController::goSyncFromServer,
        onGoBack = navController::popBackStack
    )
}

internal fun NavGraphBuilder.syncFromServer(
    onBack: () -> Unit
) = composable<SyncFromServerRoute> {
    SyncFromServerScreen(
        onBackClick = onBack,
    )
}


internal fun NavGraphBuilder.syncToServer(
    onBack: () -> Unit
) = composable<SyncToServerRoute> {
    SyncToServerScreen(
        onBackClick = onBack,
    )
}

internal fun NavController.goSyncFromServer() = navigate(SyncFromServerRoute)
internal fun NavController.goSyncToServer() = navigate(SyncToServerRoute)