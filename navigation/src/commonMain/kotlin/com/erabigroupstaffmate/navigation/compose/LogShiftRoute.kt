package com.erabigroupstaffmate.navigation.compose

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.erabigroupstaffmate.features.kiosk.logshift.screen.LogShiftScreen
import com.erabigroupstaffmate.navigation.LogShiftRoute
import kotlinx.serialization.Serializable


internal fun NavGraphBuilder.logShift(
    navController: NavHostController,
) = composable<LogShiftRoute> {
    LogShiftScreen(
        onLogEvent = { event ->
            navController.goLogResult(event)
        },
        onBackClick = navController::popBackStack,
    )
}

internal fun NavController.goLogShift(
    staffJson: String,
) = navigate(LogShiftRoute(staffJson = staffJson))
