package com.erabigroupstaffmate.features.admin.home.viewmdoel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.core.domain.AuthEventController
import com.erabigroupstaffmate.modelhub.AuthRole
import com.erabigroupstaffmate.features.admin.home.screen.AdminHomeNavActions
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AdminHomeViewModel(
    private val authEventController: AuthEventController,
) : ViewModel() {


    private val _events = Channel<AdminHomeNavActions>()

    val events = _events.receiveAsFlow()

    private val authResponseJob = authEventController
        .collectAuthResponses()
        .onEach { key ->
            handleAuthReceived(key = key)
        }.launchIn(viewModelScope)


    private suspend fun handleAuthReceived(key: AuthRole) {
        val event = key.toNavEvent() ?: return
        _events.send(event)
    }

    fun requestAuth(key: AuthRole) {
        viewModelScope.launch {
            authEventController.request(key)
        }
    }

    private fun AuthRole.toNavEvent() = when (this) {
        AuthRole.AccessStaff -> AdminHomeNavActions.Staff
        AuthRole.SetupKiosk -> AdminHomeNavActions.SetupKiosk
        AuthRole.AccessPayroll -> AdminHomeNavActions.PayRoll
        AuthRole.AccessManualAttendance -> AdminHomeNavActions.ManualAttendance
        AuthRole.AccessDeductions -> AdminHomeNavActions.Deductions
        AuthRole.AccessBorrowings -> AdminHomeNavActions.Borrowings
        else -> null
    }


}