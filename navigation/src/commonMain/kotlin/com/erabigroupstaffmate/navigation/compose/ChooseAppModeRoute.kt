package com.erabigroupstaffmate.navigation.compose

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.erabigroupstaffmate.features.shared.appmode.screen.ChooseAppModeScreen
import com.erabigroupstaffmate.modelhub.AppMode
import com.erabigroupstaffmate.navigation.routes.AdminRoute
import com.erabigroupstaffmate.navigation.routes.ChooseAppModeRoute
import com.erabigroupstaffmate.navigation.routes.KioskRoute
import com.erabigroupstaffmate.navigation.utils.navigateAndClearUntil


internal fun NavGraphBuilder.chooseAppMode(
    onSynced: (mode: AppMode) -> Unit,
) = composable<ChooseAppModeRoute> {

    ChooseAppModeScreen(
        onSynced = onSynced,
    )
}


internal fun NavController.goAfterAppMode(mode: AppMode) {
    val destination = when (mode) {
        AppMode.Kiosk -> KioskRoute
        AppMode.Admin -> AdminRoute
        AppMode.Unknown -> EmptyRoute
    }
    navigateAndClearUntil(destination = destination, popped = ChooseAppModeRoute)
}
