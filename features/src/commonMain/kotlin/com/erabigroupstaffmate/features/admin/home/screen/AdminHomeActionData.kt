package com.erabigroupstaffmate.features.admin.home.screen

import com.erabigroupstaffmate.modelhub.AuthRole
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.borrow
import com.erabigroupstaffmate.uihub.resources.borrowings
import com.erabigroupstaffmate.uihub.resources.deduct
import com.erabigroupstaffmate.uihub.resources.deductions
import com.erabigroupstaffmate.uihub.resources.ic_contactless
import com.erabigroupstaffmate.uihub.resources.ic_history
import com.erabigroupstaffmate.uihub.resources.ic_settings
import com.erabigroupstaffmate.uihub.resources.manual_attend
import com.erabigroupstaffmate.uihub.resources.manual_attendance
import com.erabigroupstaffmate.uihub.resources.payroll
import com.erabigroupstaffmate.uihub.resources.settings
import com.erabigroupstaffmate.uihub.resources.setup_kiosk
import com.erabigroupstaffmate.uihub.resources.shift_logs
import com.erabigroupstaffmate.uihub.resources.staff
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource


data class AdminHomeActionData(
    val title: StringResource,
    val icon: DrawableResource,
    val navAction: AdminHomeNavActions,
    val authRole: AuthRole? = null,
)


val adminHomeActions = listOf(
    AdminHomeActionData(
        title = Res.string.staff,
        icon = Res.drawable.staff,
        navAction = AdminHomeNavActions.Staff,
        authRole = AuthRole.AccessStaff
    ),
    AdminHomeActionData(
        title = Res.string.manual_attendance,
        icon = Res.drawable.manual_attend,
        navAction = AdminHomeNavActions.ManualAttendance,
        authRole = AuthRole.AccessManualAttendance
    ),
    AdminHomeActionData(
        title = Res.string.payroll,
        icon = Res.drawable.payroll,
        navAction = AdminHomeNavActions.PayRoll,
        authRole = AuthRole.AccessPayroll
    ),
    AdminHomeActionData(
        title = Res.string.deductions,
        icon = Res.drawable.deduct,
        navAction = AdminHomeNavActions.Deductions,
        authRole = AuthRole.AccessDeductions
    ),
    AdminHomeActionData(
        title = Res.string.shift_logs,
        icon = Res.drawable.ic_history,
        navAction = AdminHomeNavActions.Logs,
    ),
    AdminHomeActionData(
        title = Res.string.borrowings,
        icon = Res.drawable.borrow,
        navAction = AdminHomeNavActions.Borrowings,
        authRole = AuthRole.AccessBorrowings
    ),
    AdminHomeActionData(
        title = Res.string.setup_kiosk,
        icon = Res.drawable.ic_contactless,
        navAction = AdminHomeNavActions.SetupKiosk,
        authRole = AuthRole.SetupKiosk
    ),
    AdminHomeActionData(
        title = Res.string.settings,
        icon = Res.drawable.ic_settings,
        navAction = AdminHomeNavActions.Settings
    ),
)