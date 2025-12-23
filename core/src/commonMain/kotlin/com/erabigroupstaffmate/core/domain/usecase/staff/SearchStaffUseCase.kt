package com.erabigroupstaffmate.core.domain.usecase.staff

import com.erabigroupstaffmate.core.domain.repository.StaffRepository
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.ReadDeviceSettingsUseCase
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest

class SearchStaffUseCase(
    private val readDeviceSettingsUseCase: ReadDeviceSettingsUseCase,
    private val staffRepository: StaffRepository
) {
    operator fun invoke(
        query: String,
    ) = readDeviceSettingsUseCase()
        .filterNotNull().flatMapLatest {
            staffRepository.searchStaff(
                branch = it.branchId,
                chain = it.chainId,
                query = query
            )
        }
}