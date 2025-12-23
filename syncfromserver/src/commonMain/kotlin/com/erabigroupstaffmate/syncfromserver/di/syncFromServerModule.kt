package com.erabigroupstaffmate.syncfromserver.di

import com.erabigroupstaffmate.syncfromserver.data.AdminSyncFromServerPhaseFactoryImpl
import com.erabigroupstaffmate.syncfromserver.data.DefaultSyncFromServerManager
import com.erabigroupstaffmate.syncfromserver.data.phase.SyncAuthKeyFromServerPhase
import com.erabigroupstaffmate.syncfromserver.data.phase.SyncBorrowingFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.data.phase.SyncDeductFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.data.phase.SyncLogsFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.data.phase.SyncStaffFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerManager
import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhaseManager
import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhasesFactory
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val syncFromServerModule = module {
    singleOf(::AdminSyncFromServerPhaseFactoryImpl).bind(SyncFromServerPhasesFactory::class)
    singleOf(::SyncDeductFromServerPhaseManager).bind(SyncFromServerPhaseManager::class)
    singleOf(::SyncBorrowingFromServerPhaseManager).bind(SyncFromServerPhaseManager::class)
    factoryOf(::DefaultSyncFromServerManager).bind(SyncFromServerManager::class)
    factoryOf(::SyncLogsFromServerPhaseManager).bind(SyncFromServerPhaseManager::class)
    factoryOf(::SyncStaffFromServerPhaseManager).bind(SyncFromServerPhaseManager::class)
    factoryOf(::SyncAuthKeyFromServerPhase).bind(SyncFromServerPhaseManager::class)
}