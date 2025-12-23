package com.erabigroupstaffmate.features.kiosk.home

sealed interface KioskHomeNavAction {

    data object Attendance : KioskHomeNavAction
    data object Settings : KioskHomeNavAction

}