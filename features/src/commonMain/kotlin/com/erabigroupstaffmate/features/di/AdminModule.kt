package com.erabigroupstaffmate.features.di


import com.erabigroupstaffmate.core.domain.usecase.deduct.AddDeductUseCase
import com.erabigroupstaffmate.core.domain.usecase.syncer.GetUnSyncedCounterForAdminUseCase
import com.erabigroupstaffmate.core.domain.usecase.syncer.GetUnSyncedCounterUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module


val adminModule = module {
    factoryOf(::GetUnSyncedCounterForAdminUseCase).bind(GetUnSyncedCounterUseCase::class)
    factoryOf(::AddDeductUseCase)
}