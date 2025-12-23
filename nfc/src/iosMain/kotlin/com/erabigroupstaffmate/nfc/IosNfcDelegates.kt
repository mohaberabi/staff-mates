package com.erabigroupstaffmate.nfc

import com.erabigroupstaffmate.nfc.domain.NfcNotifier
import com.erabigroupstaffmate.nfc.domain.NfcTag
import com.erabigroupstaffmate.nfc.domain.NfcTagResult
import com.erabigroupstaffmate.utility.constant.NFCErrorMessages
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import com.erabigroupstaffmate.utility.utils.extensions.bytearray.toByteArray
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import platform.CoreNFC.NFCNDEFMessage
import platform.CoreNFC.NFCNDEFPayload
import platform.CoreNFC.NFCNDEFReaderSession
import platform.CoreNFC.NFCNDEFReaderSessionDelegateProtocol
import platform.CoreNFC.NFCNDEFStatusNotSupported
import platform.CoreNFC.NFCNDEFStatusReadOnly
import platform.CoreNFC.NFCNDEFStatusReadWrite
import platform.CoreNFC.NFCNDEFTagProtocol
import platform.CoreNFC.NFCTagProtocol
import platform.CoreNFC.NFCTagReaderSession
import platform.CoreNFC.NFCTagReaderSessionDelegateProtocol
import platform.Foundation.NSError
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
class NfcDelegate(
    private val notifier: NfcNotifier,
    private val dispatchers: DispatchersProvider,
) : NSObject(),
    NFCNDEFReaderSessionDelegateProtocol,
    NFCTagReaderSessionDelegateProtocol {


    private val scope = CoroutineScope(dispatchers.io + SupervisorJob())
    override fun readerSession(
        session: NFCNDEFReaderSession,
        didDetectNDEFs: List<*>
    ) {
        val message = didDetectNDEFs
            .firstOrNull() as? NFCNDEFMessage
            ?: return

        val payload = message.foldData()
        if (payload.isNullOrEmpty()) {
            println("readerSession --- didDetectNDEFs --- isNullOrEmpty --- ")
            session.invalidateSessionWithErrorMessage(errorMessage = NFCErrorMessages.CARD_DATA_EMPTY)
            return
        }
        scope.launch {
            notifier.send(NfcTagResult.Found(NfcTag(tag = message, data = payload)))
        }

    }


    override fun readerSession(
        session: NFCNDEFReaderSession,
        didInvalidateWithError: NSError
    ) {
        val error = didInvalidateWithError.localizedDescription
        session.invalidateSessionWithErrorMessage(errorMessage = error)
    }

    override fun tagReaderSession(
        session: NFCTagReaderSession,
        didDetectTags: List<*>
    ) {

        val nfcTag = didDetectTags.firstOrNull() as? NFCTagProtocol ?: return
        session.connectToTag(
            tag = nfcTag,
            completionHandler = { error ->
                error?.let {
                    println("tagReaderSession --- didDetectTags --- connectToTag ---  ${it.localizedDescription}")
                    session.invalidateSessionWithErrorMessage(
                        errorMessage = it.localizedDescription
                    )
                    return@connectToTag
                }
            },
        )

        val ndefTag = nfcTag as? NFCNDEFTagProtocol
        if (ndefTag == null) {
            println("tagReaderSession --- didDetectTags --- ndefTag=null ---")
            session.invalidateSessionWithErrorMessage(NFCErrorMessages.TAG_NOT_SUPPORT)
            return
        }

        ndefTag.queryNDEFStatusWithCompletionHandler { status, capacity, error ->
            if (error != null) {
                println("tagReaderSession --- didDetectTags --- queryNDEF ---  ${error.localizedDescription}")
                session.invalidateSessionWithErrorMessage(error.localizedDescription)
                return@queryNDEFStatusWithCompletionHandler
            }
            when (status) {
                NFCNDEFStatusReadWrite -> {
                    ndefTag.readNDEFWithCompletionHandler { message, error ->
                        scope.launch {
                            notifier.send(NfcTagResult.Found(NfcTag(nfcTag, "")))
                        }
                    }
                }

                NFCNDEFStatusReadOnly -> session.invalidateSessionWithErrorMessage(
                    NFCErrorMessages.TAG_READ_ONLY
                )

                NFCNDEFStatusNotSupported -> session.invalidateSessionWithErrorMessage(
                    NFCErrorMessages.NDEF_NOT_SUPPORT
                )


            }
        }
    }

    override fun tagReaderSessionDidBecomeActive(session: NFCTagReaderSession) {}

    override fun tagReaderSession(
        session: NFCTagReaderSession,
        didInvalidateWithError: NSError
    ) {
        val error = didInvalidateWithError.localizedDescription
        println("tagReaderSession --- didInvalidateWithError --- ${didInvalidateWithError.localizedDescription} ---")
        session.invalidateSessionWithErrorMessage(errorMessage = error)
    }
}

private fun NFCNDEFMessage.foldData(): String? {
    val records = records as? List<NFCNDEFPayload> ?: return null
    if (records.isEmpty()) return ""
    return records.firstOrNull()?.payload?.toByteArray()?.decodeToString()

}


//private fun NFCNDEFMessage.foldData(): String? {
//    val records = records as? List<NFCNDEFPayload> ?: return null
//    if (records.isEmpty()) return ""
//    return String(bytes = records[0].payload.toByteArray(), charset = Charsets.UTF_8)
//
//}
//
