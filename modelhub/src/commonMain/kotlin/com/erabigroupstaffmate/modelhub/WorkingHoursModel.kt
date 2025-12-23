package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.Serializable

//TODO get the config from the backend
@Serializable
data class WorkingHoursModel(
    val openAtHr24: Int = 8,
    val closeAtHr24: Int = 5
) {
    fun isOverNight() = closeAtHr24 < openAtHr24
}
