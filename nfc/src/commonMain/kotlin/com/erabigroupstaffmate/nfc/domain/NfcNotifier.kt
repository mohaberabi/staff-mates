package com.erabigroupstaffmate.nfc.domain

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow


sealed interface NfcTagResult {
    data class Error(val message: String) : NfcTagResult
    data class Found(val tag: NfcTag) : NfcTagResult
}

interface NfcNotifier {
    val foundTags: Flow<NfcTagResult>
    suspend fun send(result: NfcTagResult)
}


class DefaultNfcNotifier : NfcNotifier {

    private val channel = Channel<NfcTagResult>()
    override val foundTags: Flow<NfcTagResult> = channel.receiveAsFlow()
    override suspend fun send(result: NfcTagResult) {
        channel.send(result)
    }
}