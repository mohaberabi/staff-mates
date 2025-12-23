package com.erabigroupstaffmate.navigation.compose

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.erabigroupstaffmate.features.shared.settings.screen.AppLanguageScreen
import com.erabigroupstaffmate.features.shared.settings.screen.SettingsScreen
import com.erabigroupstaffmate.features.shared.settings.viewmodel.SettingsActions
import com.erabigroupstaffmate.uihub.components.dialogs.FullScreenLoader
import kotlinx.serialization.Serializable

@Serializable
data object EmptyRoute

@Serializable
internal data object SharedSettingsRoute

@Serializable
internal data object AppLangRoute

internal fun NavGraphBuilder.emptyRoute() =
    composable<EmptyRoute> { FullScreenLoader() }

internal fun NavGraphBuilder.sharedSettings(
    onActions: (SettingsActions) -> Unit,
    onBackClick: () -> Unit,
) = composable<SharedSettingsRoute> {
    SettingsScreen(
        onActions = onActions,
        onBackClick = onBackClick,
    )
}


internal fun NavGraphBuilder.appLang(
    onGoBack: () -> Unit,
) = composable<AppLangRoute> {
    AppLanguageScreen(onGoBack = onGoBack)
}
