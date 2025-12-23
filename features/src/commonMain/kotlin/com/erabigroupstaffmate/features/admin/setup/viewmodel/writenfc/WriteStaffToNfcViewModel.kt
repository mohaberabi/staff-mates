package com.erabigroupstaffmate.features.admin.setup.viewmodel.writenfc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.nfc.domain.NfcManager
import com.erabigroupstaffmate.nfc.domain.NfcTag
import com.erabigroupstaffmate.nfc.domain.StaffNfcCardManager
import com.erabigroupstaffmate.modelhub.StaffNfcCardModel
import com.erabigroupstaffmate.nfc.domain.NfcTagResult
import com.erabigroupstaffmate.utility.constant.NFC_AUTH_KEY
import com.erabigroupstaffmate.utility.extensions.onFailureNonCancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class WriteStaffToNfcViewModel(
    private val nfcManager: NfcManager,
    private val staffNfcCardManager: StaffNfcCardManager
) : ViewModel() {


    private val isWriting = MutableStateFlow(false)

    private val _events = Channel<WriteStaffToNfcEvents>()
    val events = _events.receiveAsFlow()

    private val _state = MutableStateFlow(WriteStaffToNfcState())
    val state = _state
        .onCompletion { nfcManager.stopDiscovery() }
        .stateIn(
            scope = viewModelScope,
            initialValue = WriteStaffToNfcState(),
            started = SharingStarted.WhileSubscribed()
        )
    private val tagsJob = nfcManager.tags
        .onEach { result ->
            when (result) {
                is NfcTagResult.Error -> sendEvent(WriteStaffToNfcEvents.ErrorWriting)
                is NfcTagResult.Found -> writeStaffDataToCard(result.tag)
            }

        }.launchIn(viewModelScope)

    fun onAction(action: WriteStaffToNfcActions) {
        when (action) {
            is WriteStaffToNfcActions.StaffIdChanged -> idChanged(action.id)
            WriteStaffToNfcActions.StartReading -> startReading()
        }
    }


    private fun idChanged(id: String) = _state.update { it.copy(staffId = id) }

    private fun startReading() {
        if (_state.value.isReading) return
        runCatching {
            nfcManager.tartDiscovery(isForWriting = true)
        }.onSuccess {
            _state.update { it.copy(isReading = true) }
        }.onFailureNonCancel {
            sendEvent(WriteStaffToNfcEvents.ErrorReading)
        }
    }

    private fun writeStaffDataToCard(tag: NfcTag) {
        if (isWriting.value) return
        val data = StaffNfcCardModel(
            staffId = _state.value.staffId,
            authKey = NFC_AUTH_KEY
        )
        viewModelScope.launch {
            isWriting.update { true }
            val result = staffNfcCardManager.writeData(foundTag = tag, data = data)
            if (result) {
                sendEvent(WriteStaffToNfcEvents.WritingDataDone)
            } else {
                sendEvent(WriteStaffToNfcEvents.ErrorWriting)
            }
            nfcManager.stopDiscovery()
            isWriting.update { false }
        }

    }

    private fun sendEvent(event: WriteStaffToNfcEvents) {
        viewModelScope.launch { _events.send(event) }
    }
}