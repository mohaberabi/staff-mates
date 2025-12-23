package com.erabigroupstaffmate.features.admin.home.screen

sealed interface AdminHomeNavActions {
    data object PayRoll : AdminHomeNavActions
    data object Settings : AdminHomeNavActions
    data object Deductions : AdminHomeNavActions
    data object Borrowings : AdminHomeNavActions
    data object Staff : AdminHomeNavActions
    data object SetupKiosk : AdminHomeNavActions
    data object ManualAttendance : AdminHomeNavActions
    data object Logs : AdminHomeNavActions

}