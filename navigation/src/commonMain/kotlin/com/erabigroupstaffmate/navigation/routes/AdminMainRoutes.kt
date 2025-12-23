package com.erabigroupstaffmate.navigation.routes


import kotlinx.serialization.Serializable

@Serializable

sealed interface AdminMainRoutes {

    @Serializable
    data object Payroll : AdminMainRoutes

    @Serializable
    data object Deductions : AdminMainRoutes

    @Serializable
    data object Borrowing : AdminMainRoutes

    @Serializable
    data object Staff : AdminMainRoutes

    @Serializable
    data object WriteToNfc : AdminMainRoutes

    @Serializable
    data object ReadFromNfc : AdminMainRoutes

    @Serializable
    data object SetupKiosk : AdminMainRoutes


    @Serializable
    data object ManualAttendance : AdminMainRoutes


}


