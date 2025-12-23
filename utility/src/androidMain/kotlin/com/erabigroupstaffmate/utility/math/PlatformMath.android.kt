package com.erabigroupstaffmate.utility.math

import java.math.BigDecimal

actual class PlatformBigDecimal actual constructor(value: String) {
    private val bigDecimal = BigDecimal(value)
    actual operator fun plus(other: PlatformBigDecimal): PlatformBigDecimal {
        return PlatformBigDecimal(bigDecimal.add(other.bigDecimal).toPlainString())
    }

    actual operator fun minus(other: PlatformBigDecimal): PlatformBigDecimal {
        return PlatformBigDecimal(bigDecimal.minus(other.bigDecimal).toPlainString())
    }

    actual operator fun times(other: PlatformBigDecimal): PlatformBigDecimal {
        return PlatformBigDecimal(bigDecimal.multiply(other.bigDecimal).toPlainString())
    }

    actual operator fun div(other: PlatformBigDecimal): PlatformBigDecimal {
        return PlatformBigDecimal(bigDecimal.divide(other.bigDecimal).toPlainString())
    }

    actual fun format(places: Int): String =
        PlatformDecimalFormatter()
            .format(number = bigDecimal.toDouble(), places = 2)

}

actual fun Double.format(places: Int): String =
    PlatformDecimalFormatter()
        .format(number = this, places = 2)
