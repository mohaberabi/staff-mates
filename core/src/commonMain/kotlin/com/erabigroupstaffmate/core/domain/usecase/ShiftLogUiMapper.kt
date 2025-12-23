package com.erabigroupstaffmate.core.domain.usecase

import com.erabigroupstaffmate.erabitime.domain.AppDateFormats
import com.erabigroupstaffmate.erabitime.domain.ErabiDateFormatter
import com.erabigroupstaffmate.modelhub.ShiftLogModel
import com.erabigroupstaffmate.modelhub.uidmodel.ShiftLogUiModel
import com.erabigroupstaffmate.utility.math.format

class ShiftLogUiMapper(
    private val dateFormatter: ErabiDateFormatter,
) {
    operator fun invoke(log: ShiftLogModel): ShiftLogUiModel {
        val format = AppDateFormats.DateTimeAmPm()
        val hrs = log.totalWorkedHours.format()
        val (checkIn, checkout) = with(dateFormatter) {
            val checkin = formatFromMillis(
                millis = log.logInMillis,
                format = format
            )
            val checkout = log.logOutMillis?.let {
                formatFromMillis(
                    millis = it,
                    format = format
                )
            } ?: ""
            checkin to checkout
        }

        return ShiftLogUiModel(
            id = log.id,
            staffId = log.staffId,
            checkInTime = checkIn,
            checkOutTime = checkout,
            totalHours = hrs,
            staffFullName = log.staffFullName,
        )
    }
}