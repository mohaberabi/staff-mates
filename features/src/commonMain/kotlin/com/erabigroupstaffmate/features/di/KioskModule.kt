package com.erabigroupstaffmate.features.di

import com.erabigroupstaffmate.syncfromserver.data.KioskSyncFromServerPhaseFactoryImpl
import com.erabigroupstaffmate.syncfromserver.domain.SyncFromServerPhasesFactory
import com.erabigroupstaffmate.core.domain.usecase.syncer.GetUnSyncedCounterForKioskUseCase
import com.erabigroupstaffmate.core.domain.usecase.syncer.GetUnSyncedCounterUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


val kioskModule = module {
    factoryOf(::KioskSyncFromServerPhaseFactoryImpl).bind(SyncFromServerPhasesFactory::class)
    factoryOf(::GetUnSyncedCounterForKioskUseCase).bind(GetUnSyncedCounterUseCase::class)

}