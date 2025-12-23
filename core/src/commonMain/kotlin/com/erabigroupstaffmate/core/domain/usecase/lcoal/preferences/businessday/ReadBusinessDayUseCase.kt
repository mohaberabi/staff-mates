package com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.businessday

import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.modelhub.WorkingHoursModel
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.workhours.ReadWorkHoursUseCase
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus

class GetBusinessDateUseCase(
    private val erabiTime: ErabiTime,
    private val readWorkHoursUseCase: ReadWorkHoursUseCase,
) {
    suspend operator fun invoke(): LocalDate {
        val workingHours = readWorkHoursUseCase().firstOrNull() ?: WorkingHoursModel()
        val now = erabiTime.getNowDateTimeInErabiZone()
        val openHour = workingHours.openAtHr24
        val closeHour = workingHours.closeAtHr24
        val isOvernight = closeHour <= openHour
        return if (isOvernight && now.hour < closeHour) {
            now.date.minus(DatePeriod(days = 1))
        } else {
            now.date
        }
    }
}

