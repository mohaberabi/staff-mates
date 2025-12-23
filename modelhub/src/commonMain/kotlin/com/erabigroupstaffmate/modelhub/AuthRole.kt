package com.erabigroupstaffmate.modelhub

enum class AuthRole(
    val type: Int
) {
    AccessStaff(0),
    ChangeSettings(1),
    SetupKiosk(2),
    AccessPayroll(3),
    AccessManualAttendance(4),
    AccessDeductions(5),
    AccessBorrowings(6),
    CreateDeduct(7),
    CreateBorrow(8);

    //0,1,4
    companion object Companion {
        fun fromType(valueType: Int) = entries.firstOrNull { it.type == valueType } ?: AccessStaff
    }
}