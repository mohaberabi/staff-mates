package com.erabigroupstaffmate.erabitime.domain

import com.erabigroupstaffmate.erabitime.domain.constants.ErabiTimeConst
import kotlinx.datetime.LocalDateTime

interface ErabiDateFormatter {
    fun formatFromMillis(millis: Long, format: AppDateFormats): String
    fun formatFromMillis(millis: Long, timeZone: String, format: AppDateFormats): String


    fun formatLocalDateTime(
        date: LocalDateTime,
        timeZone: String = ErabiTimeConst.DEFAULT_ZONE,
        format: AppDateFormats
    ): String
}