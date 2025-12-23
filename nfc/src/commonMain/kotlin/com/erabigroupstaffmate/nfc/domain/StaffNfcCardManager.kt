package com.erabigroupstaffmate.nfc.domain

import com.erabigroupstaffmate.modelhub.StaffNfcCardModel


interface StaffNfcCardManager {


    suspend fun writeData(foundTag: NfcTag, data: StaffNfcCardModel): Boolean
    fun readData(foundTag: NfcTag): StaffNfcCardModel?
}
