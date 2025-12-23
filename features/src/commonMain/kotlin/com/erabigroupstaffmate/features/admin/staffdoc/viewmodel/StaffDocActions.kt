package com.erabigroupstaffmate.features.admin.staffdoc.viewmodel

sealed interface StaffDocActions {


    data object GeneratePayroll : StaffDocActions
    data object GenerateDeductions : StaffDocActions
    data object GenerateBorrowing : StaffDocActions
}