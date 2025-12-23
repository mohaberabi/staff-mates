package com.erabigroupstaffmate.features.admin.adddeduct.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.core.domain.usecase.deduct.AddDeductUseCase
import com.erabigroupstaffmate.calculator.domain.StaffCalculator
import com.erabigroupstaffmate.modelhub.StaffDeductType
import com.erabigroupstaffmate.core.domain.usecase.borrow.AddBorrowUseCase
import com.erabigroupstaffmate.navigation.AddDeductRoute
import com.erabigroupstaffmate.utility.extensions.onFailureNonCancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddDeductViewModel(
    private val staffCalculator: StaffCalculator,
    private val savedStateHandle: SavedStateHandle,
    private val parser: Parser,
    private val addDeductUseCase: AddDeductUseCase,
    private val addBorrowUseCase: AddBorrowUseCase,
) : ViewModel() {

    private val type = savedStateHandle.toRoute<AddDeductRoute>().staffDeductTypeName.let {
        StaffDeductType.fromString(it)
    }

    private val _messages = Channel<AddDeductEvent>()
    val messages = _messages.receiveAsFlow()

    private val staff = parser.requireFromJson<StaffModel>(
        string = savedStateHandle.toRoute<AddDeductRoute>().staffJson
    )


    private val earnPerDay = staffCalculator.getEarnPerDay(
        shiftHrs = staff.shiftHrs,
        baseSalary = staff.baseSalary
    )

    private val _state = MutableStateFlow(
        value = AddDeductState(
            staff = staff,
            earnPerDay = earnPerDay,
            type = type
        )
    )
    val state = _state.asStateFlow()

    fun reasonChanged(reason: String) {
        _state.update { it.copy(reason = reason) }
    }

    fun amountChanged(amount: String) {
        _state.update { it.copy(amount = amount) }
    }


    fun commitDeductToStaff() = when (type) {
        StaffDeductType.Deduct -> addDeduct()
        StaffDeductType.Borrow -> addBorrow()
    }

    private fun addDeduct() {
        val reason = _state.value.reason
        val amount = _state.value.amount.toDoubleOrNull() ?: 0.0
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            runCatching {
                addDeductUseCase(staff = staff, amount = amount, reason = reason)
            }.onSuccess {
                _messages.send(AddDeductEvent.DeductSaved)
            }.onFailureNonCancel {
                _messages.send(AddDeductEvent.ErrorSavingDeduct)
            }
            _state.update { it.copy(loading = false) }

        }

    }

    private fun addBorrow() {
        val reason = _state.value.reason
        val amount = _state.value.amount.toDoubleOrNull() ?: 0.0
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            runCatching {
                addBorrowUseCase(staff = staff, amount = amount, reason = reason)
            }.onSuccess {
                _messages.send(AddDeductEvent.DeductSaved)
            }.onFailureNonCancel {
                _messages.send(AddDeductEvent.ErrorSavingDeduct)
            }
            _state.update { it.copy(loading = false) }

        }
    }

}