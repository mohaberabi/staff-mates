package com.erabigroupstaffmate.features.admin.addstaff.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.navigation.AddStaffRoute
import com.erabigroupstaffmate.parser.Parser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AddStaffViewModel(
    private val parser: Parser,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val staff = savedStateHandle.toRoute<AddStaffRoute>().staffJson
        ?.let { parser.fromJson<StaffModel>(it) }

    private val _state = MutableStateFlow(AddStaffState())

    val state = _state.asStateFlow()


    fun onAction(
        action: AddStaffActions
    ) {
        when (action) {
            is AddStaffActions.LegalNameChanged -> legalNameChanged(action.value)
            is AddStaffActions.NameChanged -> nameChanged(action.value)
            is AddStaffActions.SalaryChanged -> salaryChanged(action.value)
            is AddStaffActions.ShiftHrsChanged -> shiftHrsChanged(action.value)
            is AddStaffActions.TitleChanged -> titleChanged(action.value)
            is AddStaffActions.VacationChanged -> vacationChanged(action.value)
        }
    }

    private fun legalNameChanged(value: String) = _state.update { it.copy(legalName = value) }
    private fun nameChanged(value: String) = _state.update { it.copy(fullName = value) }
    private fun salaryChanged(value: String) = _state.update { it.copy(baseSalary = value) }
    private fun shiftHrsChanged(value: String) = _state.update { it.copy(shiftHours = value) }
    private fun titleChanged(value: String) = _state.update { it.copy(title = value) }
    private fun vacationChanged(value: String) = _state.update { it.copy(vacationDays = value) }


}