package com.erabigroupstaffmate.database.mappers

import com.erabigroupstaffmate.database.entity.ShiftLogEntity
import com.erabigroupstaffmate.modelhub.ShiftLogModel

fun ShiftLogEntity.toModel() = ShiftLogModel(
    id = id,
    staffId = staffId,
    staffFullName = staffFullName,
    logInMillis = logInMillis,
    logYear = logYear,
    logMonth = logMonth,
    logOutMillis = logOutMillis,
    totalWorkedHours = totalWorkedHours,
    businessDate = businessDate,
    chain = chain,
    branch = branch
)

fun ShiftLogModel.toEntity(isSynced: Boolean = false) = ShiftLogEntity(
    id = id,
    staffId = staffId,
    staffFullName = staffFullName,
    logInMillis = logInMillis,
    logYear = logYear,
    logMonth = logMonth,
    logOutMillis = logOutMillis,
    totalWorkedHours = totalWorkedHours,
    businessDate = businessDate,
    chain = chain,
    branch = branch,
    isSynced = isSynced
)