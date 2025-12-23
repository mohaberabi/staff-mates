package com.erabigroupstaffmate.nfc

import com.erabigroupstaffmate.nfc.domain.NfcManager
import com.erabigroupstaffmate.nfc.domain.NfcNotifier
import com.erabigroupstaffmate.nfc.domain.NfcTagDataType
import com.erabigroupstaffmate.nfc.domain.NfcTagResult
import com.erabigroupstaffmate.utility.utils.extensions.bytearray.toNSData
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import platform.CoreNFC.NFCNDEFMessage
import platform.CoreNFC.NFCNDEFPayload
import platform.CoreNFC.NFCNDEFReaderSession
import platform.CoreNFC.NFCNDEFTagProtocol
import platform.CoreNFC.NFCPollingISO14443
import platform.CoreNFC.NFCPollingISO15693
import platform.CoreNFC.NFCTagReaderSession
import platform.CoreNFC.NFCTypeNameFormatMedia
import platform.CoreNFC.wellKnownTypeTextPayloadWithString
import platform.Foundation.NSData
import platform.Foundation.NSLocale
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.dataUsingEncoding
import kotlin.coroutines.resume


@OptIn(ExperimentalForeignApi::class)
class IosNfcManager(
    private val notifier: NfcNotifier,
    private val dispatchers: DispatchersProvider,
    private val delegate: NfcDelegate
) : NfcManager {


    override val tags: Flow<NfcTagResult> = notifier.foundTags
    private var ndefSession: NFCNDEFReaderSession? = null

    private var tagSession: NFCTagReaderSession? = null

    override fun tartDiscovery(isForWriting: Boolean) {
        if (isForWriting) {
            startForWriting()
        } else {
            startForReading()
        }
    }


    private fun startForWriting() {
        if (NFCTagReaderSession.readingAvailable()) {
            tagSession = NFCTagReaderSession(
                pollingOption = NFCPollingISO14443 or NFCPollingISO15693,
                delegate = delegate,
                queue = null,
            )
            tagSession?.setAlertMessage("Hold the staff card near to the device to enable nfc reading ")
            tagSession?.beginSession()
        }
    }

    private fun startForReading() {
        if (NFCNDEFReaderSession.readingAvailable()) {
            ndefSession = NFCNDEFReaderSession(
                delegate = delegate,
                queue = null,
                invalidateAfterFirstRead = true
            )
            ndefSession?.setAlertMessage("Hold the staff card near to the device to enable nfc reading ")
            ndefSession?.beginSession()
        }
    }

    override fun stopDiscovery() {
        ndefSession?.invalidateSession()
        ndefSession = null
        tagSession?.invalidateSession()
        tagSession = null
    }

    override suspend fun writeData(
        tag: Any,
        data: String,
        type: NfcTagDataType
    ): Boolean = withContext(dispatchers.io) {
        val tagProtocol = tag as? NFCNDEFTagProtocol ?: return@withContext false
        val message = createNfcMessage(type = type, data = data)
        suspendCancellableCoroutine { continuation ->
            tagProtocol.writeNDEF(
                ndefMessage = message,
                completionHandler = { error ->
                    if (error != null) {
                        continuation.resume(false)
                    }
                },
            )
            continuation.resume(true)
        }
    }


    @OptIn(BetaInteropApi::class)
    private fun createNfcMessage(type: NfcTagDataType, data: String): NFCNDEFMessage {
        val record = when (type) {
            NfcTagDataType.Simple -> NFCNDEFPayload.wellKnownTypeTextPayloadWithString(
                data,
                NSLocale("en")
            )

            NfcTagDataType.Json -> {
                val payload = NSString.create(data)?.dataUsingEncoding(NSUTF8StringEncoding)
                val type = "application/json".encodeToByteArray().toNSData()

                NFCNDEFPayload(
                    format = NFCTypeNameFormatMedia,
                    type = type,
                    identifier = NSData(),
                    payload = requireNotNull(payload),
                )
            }
        }
        return NFCNDEFMessage(listOf(record))

    }


}

