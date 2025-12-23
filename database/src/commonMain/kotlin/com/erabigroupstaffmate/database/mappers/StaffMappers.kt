package com.erabigroupstaffmate.database.mappers


import com.erabigroupstaffmate.database.entity.StaffEntity
import com.erabigroupstaffmate.database.entity.StaffFtsEntity
import com.erabigroupstaffmate.modelhub.StaffModel


fun StaffEntity.toModel() = StaffModel(
    id = id,
    frontIdUrl = frontIdUrl,
    backIdUrl = backIdUrl,
    fullName = fullName,
    title = title,
    branchId = branchId,
    chainId = chainId,
    branchName = branchName,
    chainName = chainName,
    vacationDays = vacationDays,
    baseSalary = baseSalary,
    shiftHrs = shiftHrs,
    joinDate = joinDate,
    isActive = isActive,
    legalName = legalName,
    profilePicUrl = profilePicUrl
)

fun StaffModel.toStaffEntity() = StaffEntity(
    id = id,
    frontIdUrl = frontIdUrl,
    backIdUrl = backIdUrl,
    fullName = fullName,
    title = title,
    branchId = branchId,
    chainId = chainId,
    branchName = branchName,
    chainName = chainName,
    vacationDays = vacationDays,
    baseSalary = baseSalary,
    shiftHrs = shiftHrs,
    joinDate = joinDate,
    isActive = isActive,
    legalName = legalName,
    profilePicUrl = profilePicUrl
)

fun StaffModel.toFtsEntity() = StaffFtsEntity(
    fullName = fullName,
    title = title,
    id = id
)