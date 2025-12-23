package com.erabigroupstaffmate.navigation.routes


import kotlinx.serialization.Serializable


@Serializable
sealed interface KioskMainRoutes {
    @Serializable
    data object Attendance : KioskMainRoutes

}

