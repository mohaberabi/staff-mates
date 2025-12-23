package com.erabigroupstaffmate.features.shared.settings.viewmodel

sealed interface SettingsActions {
    data object GoAccountInfo : SettingsActions
    data object GoSyncData : SettingsActions
    data object GoAppLanguage : SettingsActions

    data object GoBusinessSettings : SettingsActions

}


