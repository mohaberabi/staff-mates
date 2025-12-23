package com.erabigroupstaffmate.navigation.compose

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.erabigroupstaffmate.features.shared.loghistory.screen.LogHistoryScreen
import kotlinx.serialization.Serializable

@Serializable
internal data object LogsHistoryRoute

internal fun NavGraphBuilder.logHistoryRoute(
    onBack: () -> Unit,
) = composable<LogsHistoryRoute> {
    LogHistoryScreen(
        onBackClick = onBack
    )
}