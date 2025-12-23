package com.erabigroupstaffmate.erabitime.domain


import com.erabigroupstaffmate.erabitime.domain.constants.DATE_ISO_HASH_FORMAT
import com.erabigroupstaffmate.erabitime.domain.constants.DATE_SLASHED_FORMAT
import com.erabigroupstaffmate.erabitime.domain.constants.DATE_TIME_24_FORMAT
import com.erabigroupstaffmate.erabitime.domain.constants.DATE_TIME_AM_FORMAT
import com.erabigroupstaffmate.erabitime.domain.constants.monthYearFormat
import com.erabigroupstaffmate.erabitime.domain.constants.timeOnlyAmPmFormat
import com.erabigroupstaffmate.erabitime.domain.constants.weekDayMonthNamYearFormat
import kotlinx.datetime.format.DateTimeFormat

import kotlinx.datetime.*

internal typealias AppDateFormat = DateTimeFormat<LocalDateTime>


sealed class AppDateFormats(open val format: String) {
    //// e.g., 2025-05-24
    data object DateIsoHash : AppDateFormats("yyyy-MM-dd")

    //24/05/2025
    data object DateSlashed : AppDateFormats("dd/MM/yyyy")

    //2025-05-24 13:45:00
    data object DateTime24 : AppDateFormats("yyyy-MM-dd HH:mm:ss")

    //01:45 PM
    data class TimeOnlyAmPm(val locale: String) : AppDateFormats("hh:mm a")

    // Sat, May 24, 2025
    data class WeekDayMonthDayYear(val locale: String) : AppDateFormats("EEE, MMM d, yyyy")


    // June 2025
    data class MonthYear(val locale: String) : AppDateFormats("MMMM yyyy")
    data class DateTimeAmPm(val locale: String = "en") : AppDateFormats("yyyy-MM-dd hh:mm a")
}

internal fun AppDateFormats.formater() = when (this) {
    AppDateFormats.DateIsoHash -> DATE_ISO_HASH_FORMAT
    AppDateFormats.DateSlashed -> DATE_SLASHED_FORMAT
    AppDateFormats.DateTime24 -> DATE_TIME_24_FORMAT
    is AppDateFormats.TimeOnlyAmPm -> timeOnlyAmPmFormat(this.locale)
    is AppDateFormats.WeekDayMonthDayYear -> weekDayMonthNamYearFormat(this.locale)
    is AppDateFormats.MonthYear -> monthYearFormat(this.locale)
    is AppDateFormats.DateTimeAmPm -> DATE_TIME_AM_FORMAT
}
