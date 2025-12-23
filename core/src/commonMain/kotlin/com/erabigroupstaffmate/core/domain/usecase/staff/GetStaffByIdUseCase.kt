package com.erabigroupstaffmate.core.domain.usecase.staff

import com.erabigroupstaffmate.core.domain.repository.StaffRepository

class GetStaffByIdUseCase(
    private val staffRepository: StaffRepository
) {


    suspend operator fun invoke(
        id: String,
        chain: String,
        branch: String
    ) = staffRepository.getById(id = id, chain = chain, branch = branch)
}