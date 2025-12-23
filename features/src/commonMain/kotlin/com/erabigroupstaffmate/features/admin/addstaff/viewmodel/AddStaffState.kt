package com.erabigroupstaffmate.features.admin.addstaff.viewmodel

import com.erabigroupstaffmate.modelhub.StaffModel

data class AddStaffState(
    val staffId: String = "",
    val legalName: String = "",
    val profilePicUrl: String = "",
    val joinDate: String = "",
    val fullName: String = "",
    val title: String = "",
    val vacationDays: String = "",
    val baseSalary: String = "",
    val shiftHours: String = "9",
    val isLoading: Boolean = false,
    val pickedImageBytes: ByteArray? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as AddStaffState

        if (isLoading != other.isLoading) return false
        if (staffId != other.staffId) return false
        if (legalName != other.legalName) return false
        if (profilePicUrl != other.profilePicUrl) return false
        if (joinDate != other.joinDate) return false
        if (fullName != other.fullName) return false
        if (title != other.title) return false
        if (vacationDays != other.vacationDays) return false
        if (baseSalary != other.baseSalary) return false
        if (shiftHours != other.shiftHours) return false
        if (!pickedImageBytes.contentEquals(other.pickedImageBytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isLoading.hashCode()
        result = 31 * result + staffId.hashCode()
        result = 31 * result + legalName.hashCode()
        result = 31 * result + profilePicUrl.hashCode()
        result = 31 * result + joinDate.hashCode()
        result = 31 * result + fullName.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + vacationDays.hashCode()
        result = 31 * result + baseSalary.hashCode()
        result = 31 * result + shiftHours.hashCode()
        result = 31 * result + (pickedImageBytes?.contentHashCode() ?: 0)
        return result
    }
}

internal fun StaffModel?.toAddStaffState() =
    if (this == null) AddStaffState()
    else AddStaffState(
        staffId = id,
        profilePicUrl = profilePicUrl,
        baseSalary = "$baseSalary",
        shiftHours = "$shiftHrs",
        fullName = fullName,
        legalName = legalName,
        vacationDays = "$vacationDays",
        title = title,
        joinDate = joinDate
    )