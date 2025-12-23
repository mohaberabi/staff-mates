package com.erabigroupstaffmate.nfc.domain


enum class NfcTagDataType {
    Simple,
    Json
}

data class NfcTag(
    val tag: Any?,
    val data: String,
)
