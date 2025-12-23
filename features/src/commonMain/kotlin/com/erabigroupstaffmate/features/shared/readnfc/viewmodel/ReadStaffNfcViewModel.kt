package com.erabigroupstaffmate.features.shared.readnfc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.nfc.domain.NfcManager
import com.erabigroupstaffmate.nfc.domain.NfcTag
import com.erabigroupstaffmate.nfc.domain.NfcTagResult
import com.erabigroupstaffmate.nfc.domain.StaffNfcCardManager
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ReadStaffNfcViewModel(
    private val nfcManager: NfcManager,
    private val staffNfcCardManager: StaffNfcCardManager
) : ViewModel() {


    private val _events = Channel<ReadStaffNfcEvents>()
    val events = _events.receiveAsFlow()

    init {
        nfcManager.tartDiscovery()
        collectTags()
    }

    private fun collectTags() {
        viewModelScope.launch {
            nfcManager.tags.collect { result ->
                when (result) {
                    is NfcTagResult.Error -> sendError(result.message)
                    is NfcTagResult.Found -> readCard(result.tag)
                }
            }
        }
    }


    private suspend fun readCard(tag: NfcTag) {
        val staffData = staffNfcCardManager.readData(tag)
        staffData?.let {
            _events.send(ReadStaffNfcEvents.CardRead(it.staffId))
        } ?: run {
            sendError("Could not read or fold  the staff data from tag")
        }

    }

    private suspend fun sendError(message: String?) {
        _events.send(ReadStaffNfcEvents.ErrorReadingCard(message ?: "Something went wrong"))
    }
}