package com.erabigroupstaffmate.core.domain.usecase

import com.erabigroupstaffmate.erabitime.domain.AppDateFormats
import com.erabigroupstaffmate.erabitime.domain.ErabiDateFormatter
import com.erabigroupstaffmate.modelhub.StaffBorrowModel
import com.erabigroupstaffmate.modelhub.StaffDeductModel
import com.erabigroupstaffmate.modelhub.uidmodel.DeductUiModel

class DeductionsUiMapper(
    private val dateFormatter: ErabiDateFormatter,
) {

    fun mapDeductions(deductions: List<StaffDeductModel>): List<DeductUiModel> =
        deductions.map { mapDeduct(it) }

    fun mapBorrowings(borrowing: List<StaffBorrowModel>): List<DeductUiModel> =
        borrowing.map { mapBorrow(it) }

    fun mapDeduct(deduct: StaffDeductModel): DeductUiModel {
        val formattedDate = dateFormatter.formatFromMillis(
            millis = deduct.deductAtMillis,
            format = AppDateFormats.DateTimeAmPm("en")
        )
        return DeductUiModel(
            id = deduct.id,
            reason = deduct.reason,
            staffId = deduct.staffId,
            deductFormattedDate = formattedDate,
            amount = deduct.amount
        )
    }

    fun mapBorrow(borrow: StaffBorrowModel): DeductUiModel {
        val formattedDate = dateFormatter.formatFromMillis(
            millis = borrow.borrowAtMillis,
            format = AppDateFormats.DateTimeAmPm("en")
        )
        return DeductUiModel(
            id = borrow.id,
            reason = borrow.reason,
            staffId = borrow.staffId,
            deductFormattedDate = formattedDate,
            amount = borrow.amount
        )
    }
}