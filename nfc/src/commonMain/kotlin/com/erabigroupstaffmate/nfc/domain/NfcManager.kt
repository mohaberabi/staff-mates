package com.erabigroupstaffmate.nfc.domain

import kotlinx.coroutines.flow.Flow

interface NfcManager {
    val tags: Flow<NfcTagResult>
    fun tartDiscovery(isForWriting: Boolean = false)
    fun stopDiscovery()
    suspend fun writeData(tag: Any, data: String, type: NfcTagDataType): Boolean
}