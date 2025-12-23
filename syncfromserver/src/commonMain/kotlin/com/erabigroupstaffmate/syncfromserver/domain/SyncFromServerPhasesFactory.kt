package com.erabigroupstaffmate.syncfromserver.domain

interface SyncFromServerPhasesFactory {
    fun create(): List<SyncFromServerPhaseManager>
}