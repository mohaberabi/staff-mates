package com.erabigroupstaffmate.nfc

import android.app.Activity
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.os.Bundle
import android.util.Log
import com.erabigroupstaffmate.nfc.domain.NfcManager
import com.erabigroupstaffmate.nfc.domain.NfcNotifier
import com.erabigroupstaffmate.nfc.domain.NfcTag
import com.erabigroupstaffmate.nfc.domain.NfcTagDataType
import com.erabigroupstaffmate.nfc.domain.NfcTagResult
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AndroidNfcManager(
    private val activity: Activity,
    private val dispatchers: DispatchersProvider,
    private val notifier: NfcNotifier
) : NfcManager, NfcAdapter.ReaderCallback {
    companion object {
        private const val TAG = "AndroidNfcManager"
        private const val JSON_MIME = "application/json"
    }

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val adapter = NfcAdapter.getDefaultAdapter(activity)

    override val tags: Flow<NfcTagResult> = notifier.foundTags

    override fun tartDiscovery(isForWriting: Boolean) {
        val options = Bundle().apply {
            putInt(NfcAdapter.EXTRA_READER_PRESENCE_CHECK_DELAY, 500)
        }
        adapter?.enableReaderMode(
            activity,
            this,
            NfcAdapter.FLAG_READER_NFC_A or
                    NfcAdapter.FLAG_READER_NFC_B or
                    NfcAdapter.FLAG_READER_NFC_F or
                    NfcAdapter.FLAG_READER_NFC_V or
                    NfcAdapter.FLAG_READER_NO_PLATFORM_SOUNDS,
            options
        )
    }

    override fun stopDiscovery() {
        adapter?.disableReaderMode(activity)
    }

    override suspend fun writeData(
        tag: Any,
        data: String,
        type: NfcTagDataType
    ): Boolean = withContext(dispatchers.io) {
        try {
            val ndefTag = tag as? Tag ?: return@withContext false
            val ndef = Ndef.get(ndefTag) ?: return@withContext false
            ndef.connect()
            val message = createNfcMessage(type = type, data = data)
            ndef.writeNdefMessage(message)
            ndef.close()
            return@withContext true
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext false
        }
    }

    override fun onTagDiscovered(tag: Tag?) {
        val ndef = Ndef.get(tag) ?: return
        try {
            ndef.connect()
            log("onTagDiscovered")
            val message = ndef.cachedNdefMessage

            if (message == null || message.records.isNullOrEmpty()) {
                log("onTagDiscovered----cachedNdefMessage---Empty tag found ")
                sendEvent(NfcTagResult.Found(NfcTag(tag = tag, data = "")))
                closeNdef(ndef)
                return
            }

            log("onTagDiscovered----cachedNdefMessage---Found--with data")
            val payload = String(message.records[0].payload, Charsets.UTF_8)
            sendEvent(NfcTagResult.Found(NfcTag(tag = tag, data = payload)))
            closeNdef(ndef)
        } catch (e: Exception) {
            log("onTagDiscovered--Error---${e.message}")
            e.printStackTrace()
        } finally {
            closeNdef(ndef)
        }
    }

    private fun sendEvent(result: NfcTagResult) {
        scope.launch { notifier.send(result) }
    }

    private fun closeNdef(ndef: Ndef) {
        try {
            ndef.close()
        } catch (e: Exception) {
            e.printStackTrace()
            log("closeNdef--Error---${e.message}")
        }
    }

    private fun createNfcMessage(type: NfcTagDataType, data: String): NdefMessage {
        val record = when (type) {
            NfcTagDataType.Simple -> NdefRecord.createTextRecord("en", data)
            NfcTagDataType.Json -> NdefRecord.createMime(
                JSON_MIME, data.toByteArray(Charsets.UTF_8)
            )
        }
        return NdefMessage(arrayOf(record))
    }

    private fun log(msg: String) = Log.d(TAG, msg)
}