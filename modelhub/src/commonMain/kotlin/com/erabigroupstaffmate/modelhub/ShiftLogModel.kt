package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.Serializable


enum class ShiftLogStatus {
    NeedToCheckIn,
    NeedToCheckout,
    AlreadyCheckedOut

}

@Serializable
data class ShiftLogModel(
    val id: String,
    val staffId: String,
    val staffFullName: String,
    val logInMillis: Long,
    val logOutMillis: Long? = null,
    val totalWorkedHours: Double = 0.0,
    val businessDate: String,
    val logMonth: String,
    val logYear: String,
    val chain: String,
    val branch: String
)

fun ShiftLogModel?.mapToStatus() = when {
    this == null -> ShiftLogStatus.NeedToCheckIn
    this.logOutMillis == null -> ShiftLogStatus.NeedToCheckout
    else -> ShiftLogStatus.AlreadyCheckedOut
}