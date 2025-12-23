package com.erabigroupstaffmate.navigation.utils

import androidx.navigation.NavController


internal fun <Destination : Any, Popped : Any> NavController.navigateAndClearUntil(
    destination: Destination,
    popped: Popped
) = navigate(route = destination) {
    popUpTo(route = popped) {
        saveState = false
        inclusive = true
    }
    restoreState = false
}