package com.erabigroupstaffmate.features.shared.appmode.viewmodel

import org.jetbrains.compose.resources.StringResource


sealed interface AppModeEvents {
    data object SyncedData : AppModeEvents
    data class Error(val message: StringResource) : AppModeEvents
}