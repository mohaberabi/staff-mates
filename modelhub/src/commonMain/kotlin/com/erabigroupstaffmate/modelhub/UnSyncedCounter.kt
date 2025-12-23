package com.erabigroupstaffmate.modelhub

data class UnSyncedCounter(
    val unSyncedLogs: Int = 0,
    val unSyncedDeduct: Int = 0,
    val unSyncedBorrow: Int = 0
)


fun UnSyncedCounter.canSync() = unSyncedBorrow > 0
        || unSyncedDeduct > 0
        || unSyncedLogs > 0