package com.erabigroupstaffmate.erabitime.data

import com.erabigroupstaffmate.erabitime.domain.AppDateFormats
import com.erabigroupstaffmate.erabitime.domain.ErabiDateFormatter
import com.erabigroupstaffmate.erabitime.domain.constants.ErabiTimeConst
import com.erabigroupstaffmate.erabitime.domain.formater
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ErabiDateFormatterImpl : ErabiDateFormatter {
    override fun formatFromMillis(
        millis: Long,
        format: AppDateFormats
    ): String = formatFromMillis(
        millis = millis,
        timeZone = ErabiTimeConst.DEFAULT_ZONE,
        format = format
    )

    override fun formatFromMillis(
        millis: Long,
        timeZone: String,
        format: AppDateFormats
    ): String {
        val zone = TimeZone.of(timeZone)
        val instant = Instant.fromEpochMilliseconds(millis)
        val dateTime = instant.toLocalDateTime(zone)
        return format.formater().format(dateTime)
    }

    override fun formatLocalDateTime(
        date: LocalDateTime,
        timeZone: String,
        format: AppDateFormats
    ): String {
        return format.formater().format(date)
    }

}