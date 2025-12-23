package com.erabigroupstaffmate.features.kiosk.logshift.viewmodel

sealed interface LogShiftEvents {

    data object CheckedOut : LogShiftEvents

    data object CheckedIn : LogShiftEvents
    data object Error : LogShiftEvents
}