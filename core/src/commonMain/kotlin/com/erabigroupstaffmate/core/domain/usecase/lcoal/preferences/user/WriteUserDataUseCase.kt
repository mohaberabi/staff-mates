package com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.user

import com.erabigroupstaffmate.modelhub.UserDataModel
import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.preferences.domain.ErabiPreferences
import com.erabigroupstaffmate.preferences.domain.PreferencesKey

class WriteUserDataUseCase(
    private val erabiPreferences: ErabiPreferences,
    private val parser: Parser,
) {

    suspend operator fun invoke(
        data: UserDataModel,
    ) {
        val json = parser.toJson(data)
        erabiPreferences.writeString(key = PreferencesKey.UserData, value = json)
    }
}



