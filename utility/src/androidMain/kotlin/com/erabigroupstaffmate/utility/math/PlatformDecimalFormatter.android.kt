package com.erabigroupstaffmate.utility.math

import java.math.RoundingMode
import java.text.DecimalFormat

actual class PlatformDecimalFormatter {
    actual fun format(number: Double, places: Int): String {
        val pattern = "0.".padEnd(places + 2, '0')
        val formatter = DecimalFormat(pattern).apply {
            roundingMode = RoundingMode.HALF_UP
        }
        return formatter.format(number)
    }
}