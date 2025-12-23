package com.erabigroupstaffmate.erabitime.data

import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.erabitime.domain.constants.ErabiTimeConst
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class ErabiTimeImpl : ErabiTime {

    companion object {
        private val ZONE = TimeZone.of(ErabiTimeConst.DEFAULT_ZONE)
    }

    override fun getCurrentTimeMillisInErabiZone(): Long {
        return Clock.System.now()
            .toLocalDateTime(ZONE)
            .toInstant(ZONE)
            .toEpochMilliseconds()
    }

    override fun getDateFromMillisInErabiZone(millis: Long): LocalDateTime {
        return Instant.fromEpochMilliseconds(millis).toLocalDateTime(ZONE)
    }

    override fun getNowDateTimeInErabiZone(): LocalDateTime {
        val nowMillis = getCurrentTimeMillisInErabiZone()
        return getDateFromMillisInErabiZone(nowMillis)
    }
}