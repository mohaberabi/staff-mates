package com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.user

import com.erabigroupstaffmate.modelhub.UserDataModel
import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.preferences.domain.ErabiPreferences
import com.erabigroupstaffmate.preferences.domain.PreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReadUserDataUseCase(
    private val erabiPreferences: ErabiPreferences,
    private val parser: Parser,
) {

    operator fun invoke(): Flow<UserDataModel?> =
        erabiPreferences.readString(PreferencesKey.UserData).map {
            parser.fromJson(it)
        }
}