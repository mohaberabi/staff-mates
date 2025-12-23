package com.erabigroupstaffmate.navigation.utils

import com.erabigroupstaffmate.features.shared.settings.viewmodel.SettingsActions
import com.erabigroupstaffmate.navigation.compose.AccountInfoRoute
import com.erabigroupstaffmate.navigation.compose.AppLangRoute
import com.erabigroupstaffmate.navigation.compose.BusinessSettingsRoute
import com.erabigroupstaffmate.navigation.compose.SyncDataRoute


internal fun SettingsActions.toRoute() = when (this) {
    SettingsActions.GoAccountInfo -> AccountInfoRoute
    SettingsActions.GoAppLanguage -> AppLangRoute
    SettingsActions.GoSyncData -> SyncDataRoute
    SettingsActions.GoBusinessSettings -> BusinessSettingsRoute

}