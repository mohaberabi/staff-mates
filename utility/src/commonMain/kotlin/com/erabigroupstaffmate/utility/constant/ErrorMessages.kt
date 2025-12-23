package com.erabigroupstaffmate.utility.constant


data object NFCErrorMessages {
    const val CARD_DATA_EMPTY = "nothing to read data is empty , please write data to card first"
    const val TAG_NOT_SUPPORT = "Tag type not supported"
    const val TAG_READ_ONLY = "tag is read only can not write data to it"
    const val NDEF_NOT_SUPPORT =
        "tag is not NDEF please try to use supported one , or use another device"
}