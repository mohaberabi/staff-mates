package com.erabigroupstaffmate.utility.extensions


val String.wildCard: String get() = "*${this}*"


fun Double.toStringAsFixed(take: Int): String {
    val string = toString()
    val parts = string.split(".")
    if (parts.isEmpty()) return ""
    val beforeDecimal = parts.getOrNull(0) ?: return ""
    val afterDecimal = parts.getOrNull(1) ?: ""
    val counted = if (afterDecimal.length <= take) {
        afterDecimal
    } else {
        afterDecimal.substring(0, take)
    }
    return buildString {
        append(beforeDecimal)
        append(".")
        append(counted)
    }
}