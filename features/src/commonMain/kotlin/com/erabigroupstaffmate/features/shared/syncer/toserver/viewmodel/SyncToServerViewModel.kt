package com.erabigroupstaffmate.features.shared.syncer.toserver.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.modelhub.UnSyncedCounter
import com.erabigroupstaffmate.core.domain.usecase.syncer.GetUnSyncedCounterUseCase
import com.erabigroupstaffmate.synctoserver.domain.SyncToServerManager
import com.erabigroupstaffmate.uihub.components.snackbar.SnackbarMessage
import com.erabigroupstaffmate.utility.extensions.onFailureNonCancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SyncToServerViewModel(
    private val syncToServer: SyncToServerManager,
    private val getUnSyncedCounterUseCase: GetUnSyncedCounterUseCase
) : ViewModel() {


    val unSyncedCounter = getUnSyncedCounterUseCase().stateIn(
        scope = viewModelScope,
        initialValue = UnSyncedCounter(),
        started = SharingStarted.WhileSubscribed()
    )
    private val _isSyncing = MutableStateFlow(false)
    val isSyncing = _isSyncing.asStateFlow()

    private val _messages = Channel<SnackbarMessage>()
    val messages = _messages.receiveAsFlow()

    fun startSync() {
        viewModelScope.launch {
            _isSyncing.update { true }
            runCatching {
                syncToServer.syncAllData()
            }.onSuccess {
                _messages.send(SnackbarMessage.Done(message = "Syncing Done "))
            }.onFailureNonCancel {
                _messages.send(SnackbarMessage.Done(message = "Syncing Error :${it.message} "))
            }
            _isSyncing.update { false }
        }
    }


}