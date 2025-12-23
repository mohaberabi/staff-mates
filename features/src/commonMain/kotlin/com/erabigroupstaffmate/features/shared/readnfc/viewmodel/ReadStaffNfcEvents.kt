package com.erabigroupstaffmate.features.shared.readnfc.viewmodel

sealed interface ReadStaffNfcEvents {

    data class ErrorReadingCard(val message: String) : ReadStaffNfcEvents
    data class CardRead(val staffId: String) : ReadStaffNfcEvents
}