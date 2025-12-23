package com.erabigroupstaffmate.synctoserver.domain

interface SyncToServerPhaseFactory {
    fun create(): List<SyncToServerPhaseManager>
}