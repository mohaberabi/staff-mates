package com.erabigroupstaffmate.navigation.compose

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.erabigroupstaffmate.features.shared.login.screen.LoginScreen
import com.erabigroupstaffmate.navigation.routes.ChooseAppModeRoute
import com.erabigroupstaffmate.navigation.routes.LoginRoute
import com.erabigroupstaffmate.navigation.utils.navigateAndClearUntil


internal fun NavGraphBuilder.login(
    onLoggedIn: () -> Unit,
) = composable<LoginRoute> {
    LoginScreen(
        onLoggedIn = onLoggedIn,
    )
}


internal fun NavController.goChooseAppMode() =
    navigateAndClearUntil(destination = ChooseAppModeRoute, popped = LoginRoute)