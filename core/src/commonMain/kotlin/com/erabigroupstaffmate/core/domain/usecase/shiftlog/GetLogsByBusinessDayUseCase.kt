package com.erabigroupstaffmate.core.domain.usecase.shiftlog

import com.erabigroupstaffmate.modelhub.ShiftLogModel
import com.erabigroupstaffmate.core.domain.repository.ShiftLogRepository
import com.erabigroupstaffmate.core.domain.usecase.ShiftLogUiMapper
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.ReadDeviceSettingsUseCase
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class GetLogsByBusinessDayUseCase(
    private val shiftLogRepository: ShiftLogRepository,
    private val readDeviceSettingsUseCase: ReadDeviceSettingsUseCase,
    private val shiftLogUiMapper: ShiftLogUiMapper,
) {
    operator fun invoke(
        day: String,
    ) = readDeviceSettingsUseCase().filterNotNull().flatMapLatest {
        shiftLogRepository.getAllByBusinessDay(
            chain = it.chainId,
            branch = it.branchId,
            day = day
        ).map { list -> list.toUiModel() }
    }


    private fun List<ShiftLogModel>.toUiModel() = map { shiftLogUiMapper(it) }
}