package com.erabigroupstaffmate.database.mappers

import com.erabigroupstaffmate.database.entity.StaffBorrowEntity
import com.erabigroupstaffmate.modelhub.StaffBorrowModel


fun StaffBorrowModel.toEntity(isSynced: Boolean) = StaffBorrowEntity(
    id = id,
    staffId = staffId,
    borrowAtMillis = borrowAtMillis,
    amount = amount,
    reason = reason,
    recordYear = recordYear,
    recordMonth = recordMonth,
    staffName = staffName,
    branch = branch,
    chain = chain,
    isSynced = isSynced
)

fun StaffBorrowEntity.toModel() = StaffBorrowModel(
    id = id,
    staffId = staffId,
    borrowAtMillis = borrowAtMillis,
    amount = amount,
    reason = reason,
    recordYear = recordYear,
    recordMonth = recordMonth,
    staffName = staffName,
    branch = branch,
    chain = chain
)