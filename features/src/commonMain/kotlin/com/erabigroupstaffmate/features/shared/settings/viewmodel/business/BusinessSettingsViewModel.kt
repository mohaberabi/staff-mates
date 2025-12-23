package com.erabigroupstaffmate.features.shared.settings.viewmodel.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.businessday.GetBusinessDateUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.workhours.ReadWorkHoursUseCase
import com.erabigroupstaffmate.core.domain.repository.businessDay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class BusinessSettingsViewModel(
    private val readWorkHoursUseCase: ReadWorkHoursUseCase,
    private val getBusinessDateUseCase: GetBusinessDateUseCase
) : ViewModel() {


    val state: StateFlow<BusinessSettingsState> = readWorkHoursUseCase()
        .map<_, BusinessSettingsState> { hours ->
            val businessDate = getBusinessDateUseCase()
            BusinessSettingsState.Loaded(
                businessDate = businessDate.businessDay(),
                startWorkHr = hours.openAtHr24.toString(),
                endWorkHr = hours.closeAtHr24.toString()
            )
        }.onStart {
            emit(BusinessSettingsState.Loading)
        }.catch {
            emit(BusinessSettingsState.Error)
        }.stateIn(
            scope = viewModelScope,
            initialValue = BusinessSettingsState.Loading,
            started = SharingStarted.WhileSubscribed(5_000)
        )


}