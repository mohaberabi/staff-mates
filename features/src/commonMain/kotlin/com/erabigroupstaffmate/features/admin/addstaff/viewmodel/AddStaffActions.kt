package com.erabigroupstaffmate.features.admin.addstaff.viewmodel

sealed interface AddStaffActions {


    data class NameChanged(val value: String) : AddStaffActions
    data class LegalNameChanged(val value: String) : AddStaffActions
    data class TitleChanged(val value: String) : AddStaffActions
    data class SalaryChanged(val value: String) : AddStaffActions
    data class ShiftHrsChanged(val value: String) : AddStaffActions
    data class VacationChanged(val value: String) : AddStaffActions
    
}