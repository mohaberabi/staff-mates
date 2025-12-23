package com.erabigroupstaffmate.core.domain.usecase.staff

import com.erabigroupstaffmate.core.domain.repository.StaffRepository

class GetStaffUseCase(
    private val staffRepository: StaffRepository
) {


    operator fun invoke(
        chain: String,
        branch: String
    ) = staffRepository.getAllStaff(branch = branch, chain = chain)
}