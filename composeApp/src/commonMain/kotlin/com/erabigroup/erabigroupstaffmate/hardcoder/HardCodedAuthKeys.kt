package com.erabigroup.erabigroupstaffmate.hardcoder

import com.erabigroupstaffmate.modelhub.AuthKeyModel
import com.erabigroupstaffmate.modelhub.AuthRole


val hardCodedAuthKeys = listOf(
    AuthKeyModel(
        name = "admin-all",
        code = "20820",
        isAdmin = true,
    ),
    AuthKeyModel(
        name = "semi-admin",
        code = "90909",
        isAdmin = false,
        roles = listOf(
            AuthRole.AccessManualAttendance,
            AuthRole.AccessBorrowings,
            AuthRole.AccessDeductions,
            AuthRole.CreateBorrow,
            AuthRole.CreateDeduct,
            AuthRole.ChangeSettings,
            AuthRole.AccessStaff,
        ).map { it.type },
    ),
    AuthKeyModel(
        name = "cashier",
        code = "90901",
        roles = listOf(
            AuthRole.AccessManualAttendance,
            AuthRole.AccessBorrowings,
            AuthRole.AccessDeductions,
            AuthRole.CreateBorrow,
            AuthRole.CreateDeduct,
            AuthRole.ChangeSettings,
            AuthRole.AccessStaff,
        ).map { it.type },
        isAdmin = false
    )
)