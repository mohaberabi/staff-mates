package com.erabigroupstaffmate.features.shared.loghistory.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.core.domain.usecase.shiftlog.GetLogsByBusinessDayUseCase
import com.erabigroupstaffmate.core.domain.repository.businessDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LogHistoryViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getLogsByBusinessDayUseCase: GetLogsByBusinessDayUseCase,
    private val erabiTime: ErabiTime,
) : ViewModel() {
    companion object {
        private const val SELECT_DATE_KEY = "selected_date_key"
    }

    private val _showDatePicker = MutableStateFlow(false)

    val showDatePicker = _showDatePicker.asStateFlow()


    val selectedDate = savedStateHandle.getStateFlow(
        key = SELECT_DATE_KEY,
        initialValue = erabiTime.getCurrentTimeMillisInErabiZone()
    ).map {
        erabiTime.getDateFromMillisInErabiZone(it).businessDay()
    }.distinctUntilChanged().stateIn(
        scope = viewModelScope,
        initialValue = erabiTime.getNowDateTimeInErabiZone().businessDay(),
        started = SharingStarted.Eagerly
    )


    val logs = selectedDate.flatMapLatest {
        getLogsByBusinessDayUseCase(day = it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed()
    )


    fun dateChanged(millis: Long) {
        savedStateHandle[SELECT_DATE_KEY] = millis
    }


    fun toggleDatePicker() = _showDatePicker.update { !it }

}