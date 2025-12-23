package com.erabigroupstaffmate.features.admin.setup.viewmodel.writenfc

sealed interface WriteStaffToNfcEvents {
    data object WritingDataDone : WriteStaffToNfcEvents
    data object ErrorWriting : WriteStaffToNfcEvents
    data object ErrorReading : WriteStaffToNfcEvents
}