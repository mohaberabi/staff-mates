package com.erabigroupstaffmate.features.shared.appmode.viewmodel

import com.erabigroupstaffmate.modelhub.AppMode

data class AppModeState(
    val isSyncing: Boolean = false,
    val selectedMode: AppMode = AppMode.Unknown
)