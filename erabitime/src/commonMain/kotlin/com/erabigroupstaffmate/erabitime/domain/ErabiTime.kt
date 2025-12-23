package com.erabigroupstaffmate.erabitime.domain

import kotlinx.datetime.LocalDateTime

interface ErabiTime {
    fun getCurrentTimeMillisInErabiZone(): Long
    fun getDateFromMillisInErabiZone(millis: Long): LocalDateTime

    fun getNowDateTimeInErabiZone(): LocalDateTime
}