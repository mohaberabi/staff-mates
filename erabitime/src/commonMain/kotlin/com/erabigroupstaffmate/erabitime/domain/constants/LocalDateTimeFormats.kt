package com.erabigroupstaffmate.erabitime.domain.constants

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char


internal val DATE_ISO_HASH_FORMAT = LocalDateTime.Format {
    year()
    char('-')
    monthNumber(padding = Padding.ZERO)
    char('-')
    dayOfMonth(padding = Padding.ZERO)
}


internal val DATE_SLASHED_FORMAT = LocalDateTime.Format {
    dayOfMonth(padding = Padding.ZERO)
    char('/')
    monthNumber(padding = Padding.ZERO)
    char('/')
    year()
}

internal val DATE_TIME_24_FORMAT = LocalDateTime.Format {
    year()
    char('-')
    monthNumber(padding = Padding.ZERO)
    char('-')
    dayOfMonth(padding = Padding.ZERO)
    char(' ')
    hour(padding = Padding.ZERO)
    char(':')
    minute(padding = Padding.ZERO)
    char(':')
    second(padding = Padding.ZERO)
}
internal val DATE_TIME_AM_FORMAT = LocalDateTime.Format {
    year()
    char('-')
    monthNumber(padding = Padding.ZERO)
    char('-')
    dayOfMonth(padding = Padding.ZERO)
    char(' ')
    amPmHour(padding = Padding.ZERO)
    char(':')
    minute(padding = Padding.ZERO)
    char(' ')
    amPmMarker(am = "am", pm = "pm")
}

internal val TIME_ONLY_24 = LocalDateTime.Format {
    hour(padding = Padding.ZERO)
    char(':')
    minute(padding = Padding.ZERO)
}

internal fun timeOnlyAmPmFormat(
    locale: String = "en",
) = LocalDateTime.Format {

    val am = when (locale) {
        "en" -> "AM"
        else -> "ص"
    }
    val pm = when (locale) {
        "en" -> "PM"
        else -> "م"
    }
    amPmHour(padding = Padding.ZERO)
    char(':')
    minute(padding = Padding.ZERO)
    char(' ')
    amPmMarker(am = am, pm = pm)
}

fun monthYearFormat(
    locale: String = "en",
) = LocalDateTime.Format {
    val month = if (locale == "en") {
        MonthNames.ENGLISH_FULL
    } else arabicMonthNames

    monthName(month)
    char(' ')
    year()
}

fun weekDayMonthNamYearFormat(
    locale: String = "en",
) = LocalDateTime.Format {
    val days = if (locale == "en") {
        DayOfWeekNames.ENGLISH_ABBREVIATED
    } else arabicDayOfWeekNames
    val month = if (locale == "en") {
        MonthNames.ENGLISH_ABBREVIATED
    } else arabicMonthNames
    dayOfWeek(days)
    chars(", ")
    monthName(month)
    char(' ')
    dayOfMonth(padding = Padding.NONE)
    chars(", ")
    year()
}