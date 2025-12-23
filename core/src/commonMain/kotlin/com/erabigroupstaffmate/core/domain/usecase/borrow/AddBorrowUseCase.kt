package com.erabigroupstaffmate.core.domain.usecase.borrow

import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.modelhub.StaffBorrowModel
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.core.domain.repository.BorrowingRepository
import com.erabigroupstaffmate.utility.uuidprovider.UuidProvider

class AddBorrowUseCase(
    private val borrowingRepository: BorrowingRepository,
    private val erabiTime: ErabiTime,
    private val uuidProvider: UuidProvider,
) {


    suspend operator fun invoke(staff: StaffModel, amount: Double, reason: String) {
        val nowMillis = erabiTime.getCurrentTimeMillisInErabiZone()
        val now = erabiTime.getNowDateTimeInErabiZone()
        val borrow = StaffBorrowModel(
            id = uuidProvider.generateUuid(),
            staffId = staff.id,
            borrowAtMillis = nowMillis,
            amount = amount,
            reason = reason,
            recordYear = "${now.year}",
            recordMonth = "${now.monthNumber}",
            branch = staff.branchId,
            chain = staff.chainId,
            staffName = staff.fullName
        )
        borrowingRepository.addBorrow(borrow = borrow)
    }
}