package com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.workhours

import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.modelhub.WorkingHoursModel
import com.erabigroupstaffmate.preferences.domain.ErabiPreferences
import com.erabigroupstaffmate.preferences.domain.PreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReadWorkHoursUseCase(
    private val parser: Parser,
    private val erabiPreferences: ErabiPreferences,
) {
    operator fun invoke(): Flow<WorkingHoursModel> = erabiPreferences.readString(
        PreferencesKey.WorkingHours,
    ).map { json -> json?.let { parser.fromJson(it) } ?: WorkingHoursModel() }
}