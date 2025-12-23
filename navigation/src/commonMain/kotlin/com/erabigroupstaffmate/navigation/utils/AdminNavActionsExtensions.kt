package com.erabigroupstaffmate.navigation.utils

import com.erabigroupstaffmate.features.admin.home.screen.AdminHomeNavActions
import com.erabigroupstaffmate.navigation.compose.LogsHistoryRoute
import com.erabigroupstaffmate.navigation.compose.SharedSettingsRoute
import com.erabigroupstaffmate.navigation.routes.AdminMainRoutes


internal fun AdminHomeNavActions.toRoute() = when (this) {
    AdminHomeNavActions.Borrowings -> AdminMainRoutes.Borrowing
    AdminHomeNavActions.Deductions -> AdminMainRoutes.Deductions
    AdminHomeNavActions.PayRoll -> AdminMainRoutes.Payroll
    AdminHomeNavActions.Settings -> SharedSettingsRoute
    AdminHomeNavActions.SetupKiosk -> AdminMainRoutes.SetupKiosk
    AdminHomeNavActions.Staff -> AdminMainRoutes.Staff
    AdminHomeNavActions.ManualAttendance -> AdminMainRoutes.ManualAttendance
    AdminHomeNavActions.Logs -> LogsHistoryRoute
}