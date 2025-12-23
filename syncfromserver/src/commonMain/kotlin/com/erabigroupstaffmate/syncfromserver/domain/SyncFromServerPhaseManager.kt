package com.erabigroupstaffmate.syncfromserver.domain

import com.erabigroupstaffmate.modelhub.PayrollPayload

interface SyncFromServerPhaseManager {
    suspend fun syncPhase(payload: PayrollPayload)
}