package com.erabigroupstaffmate.utility.math

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

actual class PlatformDecimalFormatter {
    actual fun format(number: Double, places: Int): String {
        val formatter = NSNumberFormatter().apply {
            minimumFractionDigits = places.toULong()
            maximumFractionDigits = places.toULong()
            numberStyle = NSNumberFormatterDecimalStyle
        }
        return formatter.stringFromNumber(NSNumber(number)) ?: number.toString()
    }
}