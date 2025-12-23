package com.erabigroupstaffmate.features.shared.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.WriteDeviceSettingsUseCase
import com.erabigroupstaffmate.core.domain.usecase.login.LoginUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.user.WriteUserDataUseCase
import com.erabigroupstaffmate.core.domain.usecase.login.ValidateDeviceSettingsUseCase
import com.erabigroupstaffmate.modelhub.DeviceSettingsModel
import com.erabigroupstaffmate.modelhub.UserDataModel
import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.error_invalid_email
import com.erabigroupstaffmate.uihub.uimodel.extensions.stringRes
import com.erabigroupstaffmate.utility.extensions.onFailureNonCancel
import com.erabigroupstaffmate.utility.validator.email.EmailAddressValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val emailAddressValidator: EmailAddressValidator,
    private val writeUserDataUseCase: WriteUserDataUseCase,
    private val writeDeviceSettingsUseCase: WriteDeviceSettingsUseCase,
    private val validateDeviceSettingsUseCase: ValidateDeviceSettingsUseCase,
) : ViewModel() {
    
    private val _events = Channel<LoginEvents>()
    val events = _events.receiveAsFlow()

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()


    fun emailChanged(email: String) = _state.update { it.copy(email = email) }
    fun passwordChanged(password: String) = _state.update { it.copy(password = password) }
    fun chainChanged(chain: String) = _state.update { it.copy(chain = chain) }
    fun branchChanged(branch: String) = _state.update { it.copy(branch = branch) }


    fun login() {
        viewModelScope.launch {
            val email = _state.value.email.takeIf {
                emailAddressValidator.isValidEmail(it)
            } ?: run {
                _events.send(LoginEvents.Error(Res.string.error_invalid_email))
                return@launch
            }
            val password = _state.value.password
            runCatching {
                _state.update { it.copy(loading = true) }
                loginUseCase(email = email, password = password)
            }.onSuccess {
                validateUserData(uid = it, validEmail = email)
            }.onFailureNonCancel {
                _events.send(LoginEvents.Error(it.stringRes()))
            }
            _state.update { it.copy(loading = false) }
        }
    }


    private suspend fun validateUserData(
        uid: String,
        validEmail: String,
    ) {
        runCatching {
            val chain = _state.value.chain
            val branch = _state.value.branch
            validateDeviceSettingsUseCase(chain = chain, branch = branch)
            val data = UserDataModel(email = validEmail, uid = uid)
            val settings = DeviceSettingsModel(chainId = chain, branchId = branch)
            writeUserDataUseCase(data)
            writeDeviceSettingsUseCase(settings)
        }.onSuccess {
            _events.send(LoginEvents.LoggedIn)
        }.onFailureNonCancel {
            _events.send(LoginEvents.Error(it.stringRes()))
        }
    }
}