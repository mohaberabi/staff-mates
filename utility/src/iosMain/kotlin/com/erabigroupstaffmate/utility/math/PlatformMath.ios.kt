package com.erabigroupstaffmate.utility.math

import platform.Foundation.NSDecimalNumber

actual class PlatformBigDecimal actual constructor(value: String) {
    private val bigDecimal = NSDecimalNumber(value)
    actual operator fun plus(other: PlatformBigDecimal): PlatformBigDecimal {
        return PlatformBigDecimal(
            bigDecimal.decimalNumberByAdding(other.bigDecimal).stringValue()
        )
    }

    actual operator fun minus(other: PlatformBigDecimal): PlatformBigDecimal {
        return PlatformBigDecimal(
            bigDecimal.decimalNumberBySubtracting(other.bigDecimal).stringValue()
        )
    }

    actual operator fun times(other: PlatformBigDecimal): PlatformBigDecimal {
        return PlatformBigDecimal(
            bigDecimal.decimalNumberByMultiplyingBy(other.bigDecimal).stringValue()
        )
    }


    actual operator fun div(other: PlatformBigDecimal): PlatformBigDecimal {
        return PlatformBigDecimal(
            bigDecimal.decimalNumberByDividingBy(other.bigDecimal).stringValue()
        )
    }

    actual fun format(places: Int): String =
        PlatformDecimalFormatter()
            .format(number = bigDecimal.doubleValue, places = places)
}

actual fun Double.format(places: Int): String =
    PlatformDecimalFormatter()
        .format(number = this, places = places)
