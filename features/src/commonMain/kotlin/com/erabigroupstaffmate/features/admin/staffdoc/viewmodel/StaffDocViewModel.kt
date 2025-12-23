package com.erabigroupstaffmate.features.admin.staffdoc.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.erabigroupstaffmate.core.domain.usecase.DeductionsUiMapper
import com.erabigroupstaffmate.core.domain.usecase.borrow.GetBorrowForMonthByStaffUseCase
import com.erabigroupstaffmate.core.domain.usecase.deduct.GetDeductForMonthByStaffUseCase
import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.features.admin.payroll.viewmodel.PayrollViewModel
import com.erabigroupstaffmate.features.admin.staffdoc.navigation.StaffDocRoute
import com.erabigroupstaffmate.features.admin.staffdoc.navigation.StaffDocRouteArgs
import com.erabigroupstaffmate.modelhub.PayrollSummaryModel
import com.erabigroupstaffmate.modelhub.StaffDeductType
import com.erabigroupstaffmate.papergen.domain.DeductionsFileGenerator
import com.erabigroupstaffmate.papergen.domain.PayrollFileGenerator
import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.uihub.components.date.WheelPickerMonthState
import com.erabigroupstaffmate.utility.filelauncher.InternalFileLauncher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StaffDocViewModel(
    private val payrollGenerator: PayrollFileGenerator,
    private val parser: Parser,
    private val savedStateHandle: SavedStateHandle,
    private val erabiTime: ErabiTime,
    private val internalFileLauncher: InternalFileLauncher,
    private val getDeductForMonthByStaffUseCase: GetDeductForMonthByStaffUseCase,
    private val deductionsUiMapper: DeductionsUiMapper,
    private val deductionsFileGenerator: DeductionsFileGenerator,
    private val getBorrowForMonthByStaffUseCase: GetBorrowForMonthByStaffUseCase,
) : ViewModel() {


    private val now = erabiTime.getNowDateTimeInErabiZone()

    private val _state =
        MutableStateFlow(
            StaffDocState(
                selectedYear = now.year,
                selectedMonth = WheelPickerMonthState.fromMonth(now.monthNumber)
            )
        )
    val state = _state.asStateFlow()

    private val args = savedStateHandle.toRoute<StaffDocRoute>()
        .let { parser.requireFromJson<StaffDocRouteArgs>(it.staffDocRouteArgsJson) }


    fun onAction(action: StaffDocActions) {
        when (action) {
            StaffDocActions.GenerateBorrowing -> generateDeductions(StaffDeductType.Borrow)
            StaffDocActions.GenerateDeductions -> generateDeductions(StaffDeductType.Deduct)
            StaffDocActions.GeneratePayroll -> generatePayroll()
        }
    }

    private fun generatePayroll() {
        viewModelScope.launch {
            _state.update { it.copy(isGenerating = true) }
            runCatching {
                payrollGenerator.generateForStaff(
                    payroll = args.payrollSummaryModel,
                    month = args.month,
                    year = args.year
                )
            }.onSuccess { path ->
                internalFileLauncher.launch(path)
            }
            _state.update { it.copy(isGenerating = false) }

        }
    }

    private fun generateDeductions(
        deductType: StaffDeductType
    ) {
        viewModelScope.launch {
            _state.update { it.copy(isGenerating = true) }
            runCatching {
                val mappedDeductions = getDeductionsByType(deductType)
                deductionsFileGenerator.generateDeductions(
                    deductions = mappedDeductions,
                    deductType = deductType,
                    month = args.month,
                    year = args.year,
                    staff = args.payrollSummaryModel.staff,
                )

            }.onSuccess { path ->
                internalFileLauncher.launch(path)
            }.onFailure {
                it.printStackTrace()
            }
            _state.update { it.copy(isGenerating = false) }

        }
    }

    private suspend fun getDeductionsByType(
        type: StaffDeductType,
    ) = when (type) {
        StaffDeductType.Deduct -> getDeductForMonthByStaffUseCase(
            year = args.year,
            month = args.month,
            staffId = args.payrollSummaryModel.staff.id
        ).map { deductionsUiMapper.mapDeductions(it) }

        StaffDeductType.Borrow -> getBorrowForMonthByStaffUseCase(
            year = args.year,
            month = args.month,
            staffId = args.payrollSummaryModel.staff.id
        ).map {
            deductionsUiMapper.mapBorrowings(it)
        }
    }.firstOrNull().orEmpty()
}