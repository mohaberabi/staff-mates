package com.erabigroupstaffmate.features.shared.settings.viewmodel.business


sealed interface BusinessSettingsState {
    data object Loading : BusinessSettingsState
    data object Error : BusinessSettingsState
    data class Loaded(
        val businessDate: String = "",
        val startWorkHr: String = "",
        val endWorkHr: String = ""
    ) : BusinessSettingsState
}
