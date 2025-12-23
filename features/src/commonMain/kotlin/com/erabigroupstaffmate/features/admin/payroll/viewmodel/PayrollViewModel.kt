package com.erabigroupstaffmate.features.admin.payroll.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.utility.filelauncher.InternalFileLauncher
import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.calculator.domain.PayrollSummaryCalculator
import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.ReadDeviceSettingsUseCase
import com.erabigroupstaffmate.core.domain.usecase.staff.GetStaffUseCase
import com.erabigroupstaffmate.erabitime.domain.AppDateFormats
import com.erabigroupstaffmate.papergen.domain.PayrollFileGenerator
import com.erabigroupstaffmate.uihub.components.date.WheelPickerMonthState
import com.erabigroupstaffmate.utility.extensions.onFailureNonCancel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class PayrollViewModel(
    private val erabiTime: ErabiTime,
    private val getStaffUseCase: GetStaffUseCase,
    private val readDeviceSettingsUseCase: ReadDeviceSettingsUseCase,
    private val payrollSummaryCalculator: PayrollSummaryCalculator,
    private val internalFileLauncher: InternalFileLauncher,
    private val payrollFileGenerator: PayrollFileGenerator,
) : ViewModel() {

    private val now = erabiTime.getNowDateTimeInErabiZone()
    private val _state = MutableStateFlow(
        PayrollState(
            selectedYear = now.year,
            selectedMonth = WheelPickerMonthState.fromMonth(now.monthNumber)
        )
    )
    val state = _state.asStateFlow()


    private val staff = readDeviceSettingsUseCase()
        .flatMapLatest { settings ->
            settings?.let {
                getStaffUseCase(
                    branch = settings.branchId,
                    chain = settings.chainId
                )
            } ?: flowOf(listOf())
        }

    private val staffWithDateFlow = combine(
        staff,
        _state.distinctUntilChanged { f, s ->
            f.selectedYear == s.selectedYear && f.selectedMonth.monthNumber == s.selectedMonth.monthNumber
        }
    ) { staff, date -> staff to date }

    val payroll = staffWithDateFlow.map { (allStaff, date) ->
        allStaff.map { staff ->
            val payload = PayrollPayload(
                branchId = staff.branchId,
                chainId = staff.chainId,
                year = date.selectedYear.toString(),
                month = date.selectedMonth.monthNumber.toString()
            )
            payrollSummaryCalculator.getPayrollSummary(staff = staff, payload = payload)
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = listOf(),
        started = SharingStarted.WhileSubscribed()
    )


    fun dateChanged(
        year: Int,
        month: WheelPickerMonthState,
    ) {
        _state.update {
            it.copy(
                selectedYear = year,
                selectedMonth = month
            )
        }
    }

    fun generatePayroll() {
        viewModelScope.launch {
            _state.update { it.copy(isGenerating = true) }
            runCatching {
                payrollFileGenerator.generateForAllStaff(
                    payroll = payroll.value,
                    month = _state.value.selectedMonth.monthNumber.toString(),
                    year = _state.value.selectedYear.toString()
                )
            }.onFailureNonCancel {
                it.printStackTrace()
            }.onSuccess { path ->
                internalFileLauncher.launch(absoluteFilePath = path)
            }
            _state.update { it.copy(isGenerating = false) }
        }
    }
}