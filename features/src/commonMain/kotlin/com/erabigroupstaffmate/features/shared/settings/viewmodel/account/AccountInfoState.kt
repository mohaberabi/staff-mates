package com.erabigroupstaffmate.features.shared.settings.viewmodel.account

import com.erabigroupstaffmate.modelhub.AppMode

data class AccountInfoState(
    val chain: String = "",
    val branch: String = "",
    val email: String = "",
    val mode: AppMode = AppMode.Unknown,
)

