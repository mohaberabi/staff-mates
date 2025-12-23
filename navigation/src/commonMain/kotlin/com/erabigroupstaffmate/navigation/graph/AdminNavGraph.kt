package com.erabigroupstaffmate.navigation.graph


import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.erabigroupstaffmate.features.admin.adddeduct.screen.AddDeductScreen
import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.uihub.components.common.StaffClickActions
import com.erabigroupstaffmate.features.admin.deductions.screen.DeductionsScreen
import com.erabigroupstaffmate.features.admin.home.screen.AdminHomeScreen
import com.erabigroupstaffmate.features.admin.manualattendance.screen.AdminManualAttendanceScreen
import com.erabigroupstaffmate.features.admin.payroll.screen.PayrollScreen
import com.erabigroupstaffmate.features.admin.setup.screen.SetupKioskScreen
import com.erabigroupstaffmate.features.admin.setup.screen.WriteStaffToNfcScreen
import com.erabigroupstaffmate.features.admin.staff.screens.BranchStaffScreen
import com.erabigroupstaffmate.features.admin.staffdoc.navigation.StaffDocRoute
import com.erabigroupstaffmate.features.admin.staffdoc.screen.StaffDocSheet
import com.erabigroupstaffmate.features.shared.readnfc.screen.ReadStaffNfcCardScreen
import com.erabigroupstaffmate.modelhub.StaffDeductType
import com.erabigroupstaffmate.navigation.AddDeductRoute
import com.erabigroupstaffmate.navigation.ConfirmStaffRoute
import com.erabigroupstaffmate.navigation.routes.AdminMainRoutes
import com.erabigroupstaffmate.navigation.routes.AdminRoute
import com.erabigroupstaffmate.navigation.routes.HomeRoute
import com.erabigroupstaffmate.navigation.utils.toRoute


internal fun NavGraphBuilder.adminNavigation(
    parser: Parser,
    navController: NavHostController,
) = navigation<AdminRoute>(startDestination = HomeRoute) {
    composable<HomeRoute> {
        AdminHomeScreen(
            onNavigate = { navController.navigate(it.toRoute()) },
        )
    }
    composable<AdminMainRoutes.Payroll> {
        PayrollScreen(
            onBackClick = { navController.popBackStack() },
            onGenerateDocs = {
                val json = parser.toJson(it)
                navController.navigate(StaffDocRoute(json))
            },
        )
    }

    composable<AdminMainRoutes.ReadFromNfc> {
        ReadStaffNfcCardScreen(
            onBackClick = { navController.popBackStack() },
            onReadDone = { navController.navigate(ConfirmStaffRoute(it)) }
        )
    }

    composable<AdminMainRoutes.WriteToNfc> {
        WriteStaffToNfcScreen(
            onBackClick = { navController.popBackStack() }
        )
    }

    composable<AdminMainRoutes.SetupKiosk> {
        SetupKioskScreen(
            onGoToRead = { navController.navigate(AdminMainRoutes.ReadFromNfc) },
            onGoToWrite = { navController.navigate(AdminMainRoutes.WriteToNfc) },
            onBackClick = { navController.popBackStack() }
        )
    }
    composable<AdminMainRoutes.Staff> {
        BranchStaffScreen(
            onBackClick = { navController.popBackStack() },
            onStaffAction = { action ->
                navController.goAddDeduct(parser = parser, action = action)
            }
        )
    }


    composable<AdminMainRoutes.Borrowing> {
        DeductionsScreen(
            onBackClick = { navController.popBackStack() },
            type = StaffDeductType.Borrow
        )
    }
    composable<AdminMainRoutes.Deductions> {
        DeductionsScreen(
            onBackClick = { navController.popBackStack() },
            type = StaffDeductType.Deduct
        )
    }

    composable<AddDeductRoute> {
        AddDeductScreen(
            onBackClick = { navController.popBackStack() }
        )
    }
    composable<AdminMainRoutes.ManualAttendance> {
        AdminManualAttendanceScreen(
            onBackClick = {
                navController.popBackStack()
            },
            onDone = { staffid ->
                navController.navigate(ConfirmStaffRoute(staffId = staffid))
            }
        )
    }

    dialog<StaffDocRoute> {
        StaffDocSheet(
            onDismiss = { navController.popBackStack() },
        )
    }
}

private fun NavController.goAddDeduct(
    parser: Parser,
    action: StaffClickActions,
) {
    val staff = when (action) {
        is StaffClickActions.AddBorrow -> action.staff
        is StaffClickActions.AddDeduct -> action.staff
        is StaffClickActions.Info -> action.staff
    }.let { parser.toJson(it) }
    when (action) {
        is StaffClickActions.AddBorrow -> navigate(
            route = AddDeductRoute(
                staffJson = staff,
                staffDeductTypeName = StaffDeductType.Borrow.name
            )
        )

        is StaffClickActions.AddDeduct -> navigate(
            route = AddDeductRoute(
                staffJson = staff,
                staffDeductTypeName = StaffDeductType.Deduct.name
            )
        )

        is StaffClickActions.Info -> Unit
    }
}