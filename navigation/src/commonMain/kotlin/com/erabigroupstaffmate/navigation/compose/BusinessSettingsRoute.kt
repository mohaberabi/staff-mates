package com.erabigroupstaffmate.navigation.compose

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.erabigroupstaffmate.features.shared.settings.screen.BusinessSettingsScreen
import kotlinx.serialization.Serializable


@Serializable
internal data object BusinessSettingsRoute

internal fun NavGraphBuilder.businessSettings(
    onGoBack: () -> Unit,
) = composable<BusinessSettingsRoute> {
    BusinessSettingsScreen(
        onBackClick = onGoBack
    )
}


