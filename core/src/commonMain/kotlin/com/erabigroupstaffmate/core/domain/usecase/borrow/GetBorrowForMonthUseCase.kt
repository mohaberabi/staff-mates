package com.erabigroupstaffmate.core.domain.usecase.borrow

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.core.domain.repository.BorrowingRepository
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.ReadDeviceSettingsUseCase
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest

class GetBorrowForMonthUseCase(
    private val readDeviceSettingsUseCase: ReadDeviceSettingsUseCase,
    private val borrowingRepository: BorrowingRepository
) {
    operator fun invoke(
        year: String,
        month: String,
    ) = readDeviceSettingsUseCase().filterNotNull().flatMapLatest {
        borrowingRepository.getAllBorrowByYearMonth(
            payload = PayrollPayload(
                branchId = it.branchId,
                chainId = it.chainId,
                year = year,
                month = month
            )
        )
    }
}