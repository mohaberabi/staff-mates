package com.erabigroupstaffmate.core.domain.usecase.deduct

import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.modelhub.StaffDeductModel
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.core.domain.repository.StaffDeductRepository
import com.erabigroupstaffmate.utility.uuidprovider.UuidProvider


class AddDeductUseCase(
    private val deductRepository: StaffDeductRepository,
    private val erabiTime: ErabiTime,
    private val uuidProvider: UuidProvider,
) {


    suspend operator fun invoke(staff: StaffModel, amount: Double, reason: String) {
        val nowMillis = erabiTime.getCurrentTimeMillisInErabiZone()
        val now = erabiTime.getNowDateTimeInErabiZone()
        val deduct = StaffDeductModel(
            id = uuidProvider.generateUuid(),
            staffId = staff.id,
            deductAtMillis = nowMillis,
            amount = amount,
            reason = reason,
            recordYear = "${now.year}",
            recordMonth = "${now.monthNumber}",
            branch = staff.branchId,
            chain = staff.chainId
        )
        deductRepository.addDeduct(deduct = deduct)
    }
}