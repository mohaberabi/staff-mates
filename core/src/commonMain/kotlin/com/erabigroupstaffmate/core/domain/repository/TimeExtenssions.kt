package com.erabigroupstaffmate.core.domain.repository

import com.erabigroupstaffmate.modelhub.WorkingHoursModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime


fun LocalDate.businessDay(): String = buildString {
    append(dayOfMonth.toString())
    append("-")
    append(monthNumber.toString())
    append("-")
    append(year.toString())
}

fun LocalDateTime.businessDay(): String = buildString {
    append(dayOfMonth.toString())
    append("-")
    append(monthNumber.toString())
    append("-")
    append(year.toString())
}

fun checkHrInBusinessRange(
    nowHr: Int,
    working: WorkingHoursModel,
): Boolean {
    val openHr = working.openAtHr24
    val closeHr = working.closeAtHr24
    return if (working.isOverNight()) {
        nowHr >= openHr || nowHr < closeHr
    } else {
        nowHr in openHr until closeHr
    }
}