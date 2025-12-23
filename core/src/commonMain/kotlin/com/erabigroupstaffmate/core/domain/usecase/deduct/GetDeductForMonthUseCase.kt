package com.erabigroupstaffmate.core.domain.usecase.deduct

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.core.domain.repository.StaffDeductRepository
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.ReadDeviceSettingsUseCase
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest

class GetDeductForMonthUseCase(
    private val deductRepository: StaffDeductRepository,
    private val readDeviceSettingsUseCase: ReadDeviceSettingsUseCase,
) {
    operator fun invoke(
        year: String,
        month: String,
    ) = readDeviceSettingsUseCase().filterNotNull().flatMapLatest {
        deductRepository.getAllDeductsByYearMonth(
            payload = PayrollPayload(
                branchId = it.branchId,
                chainId = it.chainId,
                year = year,
                month = month
            )
        )
    }
}