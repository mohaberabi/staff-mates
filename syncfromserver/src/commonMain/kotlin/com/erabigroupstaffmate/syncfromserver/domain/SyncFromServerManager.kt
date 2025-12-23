package com.erabigroupstaffmate.syncfromserver.domain

import com.erabigroupstaffmate.modelhub.PayrollPayload

interface SyncFromServerManager {
    suspend fun syncAllData(payload: PayrollPayload)
}


