package com.erabigroupstaffmate.core.domain.usecase.borrow

import com.erabigroupstaffmate.core.domain.repository.BorrowingRepository
import com.erabigroupstaffmate.core.domain.repository.StaffDeductRepository
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.ReadDeviceSettingsUseCase
import com.erabigroupstaffmate.modelhub.PayrollPayload
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest

class GetBorrowForMonthByStaffUseCase(
    private val borrowRepository: BorrowingRepository,
    private val readDeviceSettingsUseCase: ReadDeviceSettingsUseCase,
) {
    operator fun invoke(
        year: String,
        month: String,
        staffId: String,
    ) = readDeviceSettingsUseCase().filterNotNull().flatMapLatest {
        borrowRepository.getAllBorrowByYearMonthForStaff(
            payload = PayrollPayload(
                branchId = it.branchId,
                chainId = it.chainId,
                year = year,
                month = month
            ),
            staffId = staffId
        )
    }
}