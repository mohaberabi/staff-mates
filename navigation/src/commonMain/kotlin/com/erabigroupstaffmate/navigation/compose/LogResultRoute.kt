package com.erabigroupstaffmate.navigation.compose

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.erabigroupstaffmate.features.kiosk.logshift.screen.LogShiftResultScreen
import com.erabigroupstaffmate.features.kiosk.logshift.viewmodel.LogShiftEvents
import com.erabigroupstaffmate.navigation.utils.toLogEvent
import com.erabigroupstaffmate.navigation.utils.type
import kotlinx.serialization.Serializable

@Serializable
data class LogResultRoute(val eventType: Int)

internal fun NavGraphBuilder.logResult(
    onBack: () -> Unit,
) = composable<LogResultRoute> {
    val event = it.toRoute<LogResultRoute>().eventType.toLogEvent()
    LogShiftResultScreen(
        onConfirm = {
            repeat(2) { onBack() }
        },
        event = event
    )
}


internal fun NavController.goLogResult(event: LogShiftEvents) =
    navigate(route = LogResultRoute(eventType = event.type()))