package com.erabigroupstaffmate.features.shared.confirmstaff.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.ReadDeviceSettingsUseCase
import com.erabigroupstaffmate.core.domain.usecase.staff.GetStaffByIdUseCase
import com.erabigroupstaffmate.core.domain.usecase.staff.ValidateStaffForBranchUseCase
import com.erabigroupstaffmate.navigation.ConfirmStaffRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ConfirmStaffViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getStaffByIdUseCase: GetStaffByIdUseCase,
    private val validateStaffForBranchUseCase: ValidateStaffForBranchUseCase,
    private val readDeviceSettingsUseCase: ReadDeviceSettingsUseCase
) : ViewModel() {
    private val id = savedStateHandle.toRoute<ConfirmStaffRoute>().staffId

    private val _state = MutableStateFlow<ConfirmStaffState>(ConfirmStaffState.Loading)

    val state = _state.asStateFlow()

    init {
        initialize()
    }

    private fun initialize() {
        viewModelScope.launch {

            val settings = readDeviceSettingsUseCase().firstOrNull() ?: run {
                _state.update { ConfirmStaffState.StaffNotExist }
                return@launch
            }

            val staff = getStaffByIdUseCase(
                id = id, branch = settings.branchId,
                chain = settings.chainId
            ) ?: run {
                _state.update { ConfirmStaffState.StaffNotExist }
                return@launch
            }

            validateStaffForBranchUseCase(staff = staff).takeIf { it } ?: run {
                _state.update { ConfirmStaffState.StaffNotAllowedForBranch }
                return@launch
            }

            _state.update { ConfirmStaffState.StaffVerified(staff) }
        }
    }


}