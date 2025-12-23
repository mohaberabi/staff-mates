package com.erabigroupstaffmate.core.domain.usecase.staff

import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.modelhub.DeviceSettingsModel
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.preferences.domain.ErabiPreferences
import com.erabigroupstaffmate.preferences.domain.PreferencesKey
import kotlinx.coroutines.flow.firstOrNull

class ValidateStaffForBranchUseCase(
    private val parser: Parser,
    private val erabiPreferences: ErabiPreferences,
) {


    suspend operator fun invoke(
        staff: StaffModel,
    ): Boolean {
        val json = erabiPreferences.readString(PreferencesKey.DeviceSettings)
            .firstOrNull() ?: return false

        val settings = parser.fromJson<DeviceSettingsModel>(json)
        val branch = settings?.branchId?.takeIf { it.isNotBlank() } ?: return false
        val chain = settings.chainId.takeIf { it.isNotBlank() } ?: return false
        return staff.branchId == branch && staff.chainId == chain
    }
}