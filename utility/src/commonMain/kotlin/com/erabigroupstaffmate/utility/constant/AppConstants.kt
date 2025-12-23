package com.erabigroupstaffmate.utility.constant

const val APP_WB_SITE = "https://sites.google.com/view/erabugroupio/home"
const val AUTH_LENGTH = 5

const val PHONE_LENGTH = 11
const val SECOND_MILLIS = 1000L

const val MINUTES_MILLIS = SECOND_MILLIS * 60L

const val HOURS_MILLIS = MINUTES_MILLIS * 60L

const val DAY_IN_MONTH = 30
const val NFC_AUTH_KEY = "ERABI_GROUP_STAFF_MATE"

const val DEFAULT_TIMEOUT = 5000L
const val DEFAULT_ERR_MSG = "Something went wrong , contact branch admin"

const val CHECK_IN_TITLE = "صباح النشاط! يوم جديد بيبدأ… ورينا شطارتك \uD83D\uDCAA"
val CHECK_OUT_TITLE = buildString {
    append("شكراً على مجهودك، ارتاح كويس عشان بكرة أقوى! \uD83C\uDF19")
    append("إنت دايمًا بتسيب بصمة، شكرًا على تعبك \uD83D\uDE4F")
}

fun getDifferenceInHrs(lastMillis: Long?, firstMillis: Long): Double {
    if (lastMillis == null) return 0.0
    val difference = lastMillis - firstMillis
    if (difference <= 0) return 0.0
    return difference.toDouble() / HOURS_MILLIS
}

const val STAFF_STORAGE_BUCKET = "staff_meta_data"