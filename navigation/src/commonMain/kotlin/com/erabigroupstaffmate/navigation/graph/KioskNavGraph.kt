package com.erabigroupstaffmate.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.erabigroupstaffmate.features.kiosk.home.KioskHomeScreen
import com.erabigroupstaffmate.features.shared.readnfc.screen.ReadStaffNfcCardScreen
import com.erabigroupstaffmate.navigation.ConfirmStaffRoute
import com.erabigroupstaffmate.navigation.routes.HomeRoute
import com.erabigroupstaffmate.navigation.routes.KioskMainRoutes
import com.erabigroupstaffmate.navigation.routes.KioskRoute
import com.erabigroupstaffmate.navigation.utils.toRoute


fun NavGraphBuilder.kioskNavigation(
    navController: NavController,
) = navigation<KioskRoute>(startDestination = HomeRoute) {
    composable<HomeRoute> {
        KioskHomeScreen(
            onNavAction = { navController.navigate(it.toRoute()) },
        )
    }
    composable<KioskMainRoutes.Attendance> {
        ReadStaffNfcCardScreen(
            onBackClick = { navController.popBackStack() },
            onReadDone = {
                navController.navigate(ConfirmStaffRoute(staffId = it))
            },
        )
    }
}