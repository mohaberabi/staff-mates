package com.erabigroupstaffmate.features.admin.deductions.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.modelhub.StaffDeductType
import com.erabigroupstaffmate.core.domain.usecase.borrow.GetBorrowForMonthUseCase
import com.erabigroupstaffmate.core.domain.usecase.DeductionsUiMapper
import com.erabigroupstaffmate.core.domain.usecase.deduct.GetDeductForMonthUseCase
import com.erabigroupstaffmate.core.domain.usecase.staff.SearchStaffUseCase
import com.erabigroupstaffmate.modelhub.uidmodel.DeductUiModel
import com.erabigroupstaffmate.erabitime.data.utils.extensions.distinctByYearMonth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


class DeductionsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val searchStaffUseCase: SearchStaffUseCase,
    private val getDeductForMonthUseCase: GetDeductForMonthUseCase,
    private val getBorrowForMonthUseCase: GetBorrowForMonthUseCase,
    private val erabiTime: ErabiTime,
    private val deductionsMapper: DeductionsUiMapper,
    private val type: StaffDeductType,
) : ViewModel() {
    companion object {
        private const val SEARCH_KEY = "search_staff_key"
        private const val SELECT_DATE_KEY = "selected_date_key"
    }


    private val _selectedDeduct = MutableStateFlow<DeductUiModel?>(null)

    val selectedDeduct = _selectedDeduct.asStateFlow()

    private val _showDatePicker = MutableStateFlow(false)
    val showDatePicker = _showDatePicker.asStateFlow()

    val searchQuery = savedStateHandle.getStateFlow(SEARCH_KEY, "")


    val selectedDate = savedStateHandle.getStateFlow(
        key = SELECT_DATE_KEY,
        initialValue = erabiTime.getCurrentTimeMillisInErabiZone()
    ).map {
        erabiTime.getDateFromMillisInErabiZone(it)
    }.distinctByYearMonth().stateIn(
        scope = viewModelScope,
        initialValue = erabiTime.getNowDateTimeInErabiZone(),
        started = SharingStarted.Eagerly
    )

    val staff = searchQuery.flatMapLatest { query ->
        searchStaffUseCase(query).map { list -> list.associateBy { it.id } }
    }.stateIn(
        scope = viewModelScope,
        initialValue = mapOf(),
        started = SharingStarted.WhileSubscribed()
    )

    val deductions = selectedDate.flatMapLatest {
        createDeductFlow(
            year = it.year.toString(),
            month = it.monthNumber.toString()
        ).map { list -> list.groupBy { deduct -> deduct.staffId } }
    }.stateIn(
        scope = viewModelScope,
        initialValue = mapOf(),
        started = SharingStarted.WhileSubscribed()
    )

    fun toggleDeduct(deduct: DeductUiModel?) {
        _selectedDeduct.update { deduct }
    }

    fun dateChanged(millis: Long) {
        savedStateHandle[SELECT_DATE_KEY] = millis
    }

    fun queryChanged(q: String) {
        savedStateHandle[SEARCH_KEY] = q
    }

    fun toggleDatePicker() = _showDatePicker.update { !it }


    private fun createDeductFlow(year: String, month: String) = when (type) {
        StaffDeductType.Deduct -> getDeductForMonthUseCase(
            year = year,
            month = month
        ).map { deductionsMapper.mapDeductions(deductions = it) }

        StaffDeductType.Borrow -> getBorrowForMonthUseCase(
            year = year,
            month = month
        ).map { deductionsMapper.mapBorrowings(borrowing = it) }
    }
}