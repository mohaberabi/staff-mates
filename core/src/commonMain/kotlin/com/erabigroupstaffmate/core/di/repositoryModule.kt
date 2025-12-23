package com.erabigroupstaffmate.core.di

import com.erabigroupstaffmate.core.data.repository.OfflineFirestStaffRepository
import com.erabigroupstaffmate.core.data.repository.OfflineFirstAuthKeyRepository
import com.erabigroupstaffmate.core.data.repository.OfflineFirstBorrowRepository
import com.erabigroupstaffmate.core.data.repository.OfflineFirstDeductRepository
import com.erabigroupstaffmate.core.data.repository.OfflineFirstShiftLogRepository
import com.erabigroupstaffmate.core.domain.repository.AuthKeyRepository
import com.erabigroupstaffmate.core.domain.repository.BorrowingRepository
import com.erabigroupstaffmate.core.domain.repository.ShiftLogRepository
import com.erabigroupstaffmate.core.domain.repository.StaffDeductRepository
import com.erabigroupstaffmate.core.domain.repository.StaffRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::OfflineFirstBorrowRepository).bind(BorrowingRepository::class)
    singleOf(::OfflineFirstDeductRepository).bind(StaffDeductRepository::class)
    singleOf(::OfflineFirstShiftLogRepository).bind(ShiftLogRepository::class)
    singleOf(::OfflineFirestStaffRepository).bind(StaffRepository::class)
    singleOf(::OfflineFirstAuthKeyRepository).bind(AuthKeyRepository::class)

}