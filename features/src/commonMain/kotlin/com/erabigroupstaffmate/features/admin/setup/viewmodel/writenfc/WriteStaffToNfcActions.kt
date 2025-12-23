package com.erabigroupstaffmate.features.admin.setup.viewmodel.writenfc

sealed interface WriteStaffToNfcActions {


    data object StartReading : WriteStaffToNfcActions

    data class StaffIdChanged(val id: String) : WriteStaffToNfcActions

}