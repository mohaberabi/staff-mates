package com.erabigroupstaffmate.database.mappers

import com.erabigroupstaffmate.database.entity.StaffDeductEntity
import com.erabigroupstaffmate.modelhub.StaffDeductModel

fun StaffDeductModel.toEntity(isSynced: Boolean) = StaffDeductEntity(
    id = id,
    staffId = staffId,
    deductAtMillis = deductAtMillis,
    amount = amount,
    reason = reason,
    recordYear = recordYear,
    recordMonth = recordMonth,
    branch = branch,
    chain = chain,
    isSynced = isSynced
)

fun StaffDeductEntity.toModel() = StaffDeductModel(
    id = id,
    staffId = staffId,
    deductAtMillis = deductAtMillis,
    amount = amount,
    reason = reason,
    recordYear = recordYear,
    recordMonth = recordMonth,
    branch = branch,
    chain = chain
)