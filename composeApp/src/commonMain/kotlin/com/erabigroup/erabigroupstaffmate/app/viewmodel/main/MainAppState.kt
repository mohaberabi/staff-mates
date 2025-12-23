package com.erabigroup.erabigroupstaffmate.app.viewmodel.main

import com.erabigroupstaffmate.utility.localizations.AppLang

sealed interface MainAppState {


    data object Initializing : MainAppState
    data class Initialized(
        val lang: AppLang,
    ) : MainAppState
}