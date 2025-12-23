package com.erabigroupstaffmate.navigation.compose

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.erabigroupstaffmate.features.shared.settings.screen.AccountInfoScreen
import kotlinx.serialization.Serializable

@Serializable
data object AccountInfoRoute

fun NavGraphBuilder.accountInfo(
    onBackClick: () -> Unit
) = composable<AccountInfoRoute>() {
    AccountInfoScreen(
        onBackClick = onBackClick,
    )
}
