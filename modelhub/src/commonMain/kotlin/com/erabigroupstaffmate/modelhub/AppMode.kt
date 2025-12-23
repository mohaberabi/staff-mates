package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
enum class AppMode(

) {
    @SerialName("Kiosk")
    Kiosk(),

    @SerialName("Admin")
    Admin,

    @SerialName("Unknown")
    Unknown;

    fun isUnknown() = this == Unknown

}