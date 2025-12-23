package com.erabigroupstaffmate.features.admin.staff.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erabigroupstaffmate.core.domain.usecase.staff.SearchStaffUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class BranchStaffViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val searchStaffUseCase: SearchStaffUseCase
) : ViewModel() {
    companion object {
        const val SEARCH_KEY = "search_staff_key"
    }

    val queryState = savedStateHandle.getStateFlow(SEARCH_KEY, "")


    val staff = queryState
        .flatMapLatest { searchStaffUseCase(query = it) }
        .stateIn(
            scope = viewModelScope,
            initialValue = listOf(),
            started = SharingStarted.WhileSubscribed()
        )

    fun queryChanged(q: String) {
        savedStateHandle[SEARCH_KEY] = q
    }

}