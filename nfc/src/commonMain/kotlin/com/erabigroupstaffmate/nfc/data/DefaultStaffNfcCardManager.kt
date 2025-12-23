package com.erabigroupstaffmate.nfc.data

import com.erabigroupstaffmate.modelhub.StaffNfcCardModel
import com.erabigroupstaffmate.nfc.domain.NfcManager
import com.erabigroupstaffmate.nfc.domain.NfcTag
import com.erabigroupstaffmate.nfc.domain.NfcTagDataType
import com.erabigroupstaffmate.nfc.domain.StaffNfcCardManager
import com.erabigroupstaffmate.parser.Parser

class DefaultStaffNfcCardManager(
    private val parser: Parser,
    private val nfcManager: NfcManager
) : StaffNfcCardManager {
    override suspend fun writeData(
        foundTag: NfcTag,
        data: StaffNfcCardModel
    ): Boolean {
        val tag = foundTag.tag ?: return false
        val json = parser.toJson(data)
        return nfcManager.writeData(tag = tag, data = json, type = NfcTagDataType.Json)
    }

    override fun readData(foundTag: NfcTag): StaffNfcCardModel? =
        runCatching {
            parser.fromJson<StaffNfcCardModel>(foundTag.data)
        }.onFailure {
            it.printStackTrace()
        }.getOrNull()
}