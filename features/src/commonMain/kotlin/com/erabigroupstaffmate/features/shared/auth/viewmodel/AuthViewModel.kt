package com.erabigroupstaffmate.features.shared.auth.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.erabigroupstaffmate.core.domain.AuthEventController
import com.erabigroupstaffmate.modelhub.AuthRole
import com.erabigroupstaffmate.core.domain.usecase.authkeys.AuthState
import com.erabigroupstaffmate.core.domain.usecase.authkeys.AuthStatus
import com.erabigroupstaffmate.core.domain.usecase.authkeys.IsAuthorizedUseCase
import com.erabigroupstaffmate.navigation.AuthRoute
import com.erabigroupstaffmate.utility.extensions.onFailureNonCancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    private val authEventController: AuthEventController,
) : ViewModel() {


    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    private val authType = savedStateHandle.toRoute<AuthRoute>().authKeyType
    private val authKey = AuthRole.fromType(authType)

    fun codeChanged(code: String) = _state.update { it.copy(code = code) }

    fun authorize() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            runCatching {
                isAuthorizedUseCase(
                    code = _state.value.code,
                    key = authKey
                )
            }.onSuccess { isAuthed ->
                when {
                    isAuthed -> handleAuthed()
                    else -> handeNotAuthed()
                }

            }.onFailureNonCancel { handeNotAuthed() }
            _state.update { it.copy(isLoading = false) }
        }
    }

    private suspend fun handeNotAuthed() {
        _state.update { it.copy(status = AuthStatus.NonAuthed) }
        delay(800)
        _state.update { it.copy(status = AuthStatus.Initial, code = "") }
    }

    private suspend fun handleAuthed() {
        authEventController.respond(authKey)
        _state.update { it.copy(status = AuthStatus.Authed) }
    }
}