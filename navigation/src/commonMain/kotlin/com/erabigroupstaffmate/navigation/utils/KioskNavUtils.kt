package com.erabigroupstaffmate.navigation.utils

import com.erabigroupstaffmate.features.kiosk.home.KioskHomeNavAction
import com.erabigroupstaffmate.features.kiosk.logshift.viewmodel.LogShiftEvents
import com.erabigroupstaffmate.navigation.compose.SharedSettingsRoute
import com.erabigroupstaffmate.navigation.routes.KioskMainRoutes


internal fun LogShiftEvents.type() = when (this) {
    LogShiftEvents.CheckedIn -> 0
    LogShiftEvents.CheckedOut -> 1
    LogShiftEvents.Error -> 2
}

internal fun Int.toLogEvent() = when (this) {
    0 -> LogShiftEvents.CheckedIn
    1 -> LogShiftEvents.CheckedOut
    else -> LogShiftEvents.Error
}

internal fun KioskHomeNavAction.toRoute() = when (this) {
    KioskHomeNavAction.Settings -> SharedSettingsRoute
    KioskHomeNavAction.Attendance -> KioskMainRoutes.Attendance
}