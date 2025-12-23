package com.erabigroupstaffmate.utility.math


expect class PlatformBigDecimal(value: String) {
    operator fun plus(other: PlatformBigDecimal): PlatformBigDecimal
    operator fun minus(other: PlatformBigDecimal): PlatformBigDecimal
    operator fun times(other: PlatformBigDecimal): PlatformBigDecimal
    operator fun div(other: PlatformBigDecimal): PlatformBigDecimal
    fun format(places: Int): String
}

expect fun Double.format(places: Int = 2): String

fun Double.toPlatformDecimal() = PlatformBigDecimal(this.toString())
