package com.erabigroupstaffmate.features.kiosk.logshift.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.modelhub.ShiftLogModel
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.modelhub.WorkingHoursModel
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.businessday.GetBusinessDateUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.workhours.ReadWorkHoursUseCase
import com.erabigroupstaffmate.core.domain.usecase.shiftlog.GetShiftByDayForStaffUseCase
import com.erabigroupstaffmate.core.domain.usecase.shiftlog.CheckStaffInUseCase
import com.erabigroupstaffmate.core.domain.usecase.shiftlog.CheckStaffOutUseCase
import com.erabigroupstaffmate.utility.extensions.onFailureNonCancel
import com.erabigroupstaffmate.core.domain.repository.businessDay
import com.erabigroupstaffmate.core.domain.repository.checkHrInBusinessRange
import com.erabigroupstaffmate.navigation.LogShiftRoute
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LogShiftViewModel(
    private val getBusinessDateUseCase: GetBusinessDateUseCase,
    private val readWorkHoursUseCase: ReadWorkHoursUseCase,
    private val savedStateHandle: SavedStateHandle,
    private val checkStaffIn: CheckStaffInUseCase,
    private val checkStaffOut: CheckStaffOutUseCase,
    private val erabiTime: ErabiTime,
    private val getShiftBusinessDateUseCase: GetShiftByDayForStaffUseCase,
    private val parser: Parser,
) : ViewModel() {


    private val _events = Channel<LogShiftEvents>()
    val events = _events.receiveAsFlow()

    private var didLoadBefore = false

    private val staffJson = savedStateHandle.toRoute<LogShiftRoute>().staffJson

    private val _isLogging = MutableStateFlow(false)
    val isLogging = _isLogging.asStateFlow()

    private val _state = MutableStateFlow<LogShiftState>(LogShiftState.Loading)

    val state = _state.onStart {
        if (!didLoadBefore) {
            didLoadBefore = true
            initData()
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = LogShiftState.Loading,
        started = SharingStarted.WhileSubscribed()
    )


    private fun initData() {
        viewModelScope.launch {
            runCatching {
                val workHrs = readWorkHoursUseCase().firstOrNull() ?: WorkingHoursModel()
                val now = erabiTime.getNowDateTimeInErabiZone()
                val inBusinessRange = checkHrInBusinessRange(nowHr = now.hour, working = workHrs)
                if (!inBusinessRange) {
                    _state.update { LogShiftState.NotInBusinessHours }
                } else {
                    handleReadyToSubmit()
                }
            }.onFailureNonCancel { _state.update { LogShiftState.Error } }
        }
    }


    private suspend fun handleReadyToSubmit() {
        val staff = parser.requireFromJson<StaffModel>(staffJson)
        val businessDate = getBusinessDateUseCase().businessDay()
        val shift = getShiftBusinessDateUseCase(
            staffId = staff.id,
            businessDate = businessDate
        )
        val ready = LogShiftState.ReadyToSubmit(
            staff = staff,
            shift = shift,
            businessDate = businessDate
        )
        _state.update { ready }
    }

    fun checkIn(businessDate: String) {
        viewModelScope.launch {
            _isLogging.update { true }
            runCatching {
                val staff = parser.requireFromJson<StaffModel>(staffJson)
                checkStaffIn(
                    staff = staff,
                    businessDate = businessDate,
                )
            }.onSuccess {
                _events.send(LogShiftEvents.CheckedIn)
            }.onFailureNonCancel {
                _events.send(LogShiftEvents.Error)
            }
            _isLogging.update { false }
        }
    }

    fun checkOut(previousLog: ShiftLogModel) {
        viewModelScope.launch {
            _isLogging.update { true }
            runCatching {
                checkStaffOut(previous = previousLog)
            }.onSuccess {
                _events.send(LogShiftEvents.CheckedOut)
            }.onFailureNonCancel {
                _events.send(LogShiftEvents.Error)
            }
            _isLogging.update { false }

        }
    }

}

