package com.erabigroupstaffmate.synctoserver.di

import com.erabigroupstaffmate.synctoserver.data.AdminSyncToServerPhaseFactory
import com.erabigroupstaffmate.synctoserver.data.DefaultSyncToServerManager
import com.erabigroupstaffmate.synctoserver.data.KioskSyncToServerPhaseFactory
import com.erabigroupstaffmate.synctoserver.data.phase.SyncBorrowingToServerPhaseManager
import com.erabigroupstaffmate.synctoserver.data.phase.SyncDeductToServerPhaseManager
import com.erabigroupstaffmate.synctoserver.data.phase.SyncLogsToServerPhaseManager
import com.erabigroupstaffmate.synctoserver.domain.SyncToServerManager
import com.erabigroupstaffmate.synctoserver.domain.SyncToServerPhaseFactory
import com.erabigroupstaffmate.synctoserver.domain.SyncToServerPhaseManager
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val syncToServerModule = module {

    factoryOf(::DefaultSyncToServerManager).bind(SyncToServerManager::class)
    factoryOf(::SyncLogsToServerPhaseManager).bind(SyncToServerPhaseManager::class)
    singleOf(::SyncDeductToServerPhaseManager).bind(SyncToServerPhaseManager::class)
    singleOf(::SyncBorrowingToServerPhaseManager).bind(SyncToServerPhaseManager::class)
    singleOf(::AdminSyncToServerPhaseFactory).bind(SyncToServerPhaseFactory::class)
    factoryOf(::KioskSyncToServerPhaseFactory).bind(SyncToServerPhaseFactory::class)

}