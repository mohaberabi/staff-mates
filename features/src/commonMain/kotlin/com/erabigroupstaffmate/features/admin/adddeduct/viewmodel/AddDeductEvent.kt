package com.erabigroupstaffmate.features.admin.adddeduct.viewmodel

sealed interface AddDeductEvent {


    data object DeductSaved : AddDeductEvent

    data object ErrorSavingDeduct : AddDeductEvent
}