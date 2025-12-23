package com.erabigroupstaffmate.core.domain.usecase.syncer

import com.erabigroupstaffmate.modelhub.UnSyncedCounter
import com.erabigroupstaffmate.core.domain.repository.BorrowingRepository
import com.erabigroupstaffmate.core.domain.repository.ShiftLogRepository
import com.erabigroupstaffmate.core.domain.repository.StaffDeductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class GetUnSyncedCounterForKioskUseCase(
    private val shiftLogRepository: ShiftLogRepository,
) : GetUnSyncedCounterUseCase {

    override operator fun invoke() = shiftLogRepository.getNonSyncedCount().map {
        UnSyncedCounter(unSyncedLogs = it)
    }
}

class GetUnSyncedCounterForAdminUseCase(
    private val deductRepository: StaffDeductRepository,
    private val borrowRepository: BorrowingRepository,
    private val shiftLogRepository: ShiftLogRepository,
) : GetUnSyncedCounterUseCase {

    override operator fun invoke() = combine(
        deductRepository.getNonSyncedCount(),
        borrowRepository.getNonSyncedCount(),
        shiftLogRepository.getNonSyncedCount(),
    ) { deduct, borrow, logs ->
        UnSyncedCounter(
            unSyncedBorrow = borrow,
            unSyncedDeduct = deduct,
            unSyncedLogs = logs
        )
    }
}

interface GetUnSyncedCounterUseCase {
    operator fun invoke(): Flow<UnSyncedCounter>
}