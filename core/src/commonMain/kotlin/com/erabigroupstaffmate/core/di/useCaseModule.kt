package com.erabigroupstaffmate.core.di

import com.erabigroupstaffmate.core.domain.factory.ShiftLogFactory
import com.erabigroupstaffmate.core.domain.usecase.ShiftLogUiMapper
import com.erabigroupstaffmate.core.domain.usecase.authkeys.IsAuthorizedUseCase
import com.erabigroupstaffmate.core.domain.usecase.borrow.AddBorrowUseCase
import com.erabigroupstaffmate.core.domain.usecase.borrow.GetBorrowForMonthByStaffUseCase
import com.erabigroupstaffmate.core.domain.usecase.borrow.GetBorrowForMonthUseCase
import com.erabigroupstaffmate.core.domain.usecase.deduct.GetDeductForMonthByStaffUseCase
import com.erabigroupstaffmate.core.domain.usecase.deduct.GetDeductForMonthUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.appmode.ReadAppModeUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.appmode.WriteAppModeUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.businessday.GetBusinessDateUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.ReadDeviceSettingsUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.device.WriteDeviceSettingsUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.locale.ReadAppLanguageUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.locale.WriteAppLanguageUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.user.ReadUserDataUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.user.WriteUserDataUseCase
import com.erabigroupstaffmate.core.domain.usecase.lcoal.preferences.workhours.ReadWorkHoursUseCase
import com.erabigroupstaffmate.core.domain.usecase.login.LoginUseCase
import com.erabigroupstaffmate.core.domain.usecase.login.ValidateDeviceSettingsUseCase
import com.erabigroupstaffmate.core.domain.usecase.shiftlog.CheckStaffInUseCase
import com.erabigroupstaffmate.core.domain.usecase.shiftlog.CheckStaffOutUseCase
import com.erabigroupstaffmate.core.domain.usecase.shiftlog.GetLogsByBusinessDayUseCase
import com.erabigroupstaffmate.core.domain.usecase.shiftlog.GetShiftByDayForStaffUseCase
import com.erabigroupstaffmate.core.domain.usecase.staff.GetStaffByIdUseCase
import com.erabigroupstaffmate.core.domain.usecase.staff.GetStaffUseCase
import com.erabigroupstaffmate.core.domain.usecase.staff.SearchStaffUseCase
import com.erabigroupstaffmate.core.domain.usecase.staff.ValidateStaffForBranchUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val useCaseModule = module {
    factoryOf(::ReadDeviceSettingsUseCase)
    factoryOf(::WriteDeviceSettingsUseCase)
    factoryOf(::GetStaffUseCase)
    factoryOf(::SearchStaffUseCase)
    factoryOf(::ReadAppModeUseCase)
    factoryOf(::WriteAppModeUseCase)
    factoryOf(::GetStaffByIdUseCase)
    factoryOf(::ShiftLogFactory)
    factoryOf(::GetShiftByDayForStaffUseCase)
    factoryOf(::ValidateStaffForBranchUseCase)
    factoryOf(::GetBusinessDateUseCase)
    factoryOf(::ReadWorkHoursUseCase)
    factoryOf(::ReadAppLanguageUseCase)
    factoryOf(::WriteAppLanguageUseCase)
    factoryOf(::GetDeductForMonthUseCase)
    factoryOf(::GetBorrowForMonthByStaffUseCase)
    factoryOf(::CheckStaffOutUseCase)
    factoryOf(::CheckStaffInUseCase)
    factoryOf(::GetShiftByDayForStaffUseCase)
    factoryOf(::IsAuthorizedUseCase)
    factoryOf(::AddBorrowUseCase)
    factoryOf(::GetBorrowForMonthUseCase)
    factoryOf(::GetLogsByBusinessDayUseCase)
    factoryOf(::ShiftLogUiMapper)
    factoryOf(::WriteUserDataUseCase)
    factoryOf(::ReadUserDataUseCase)
    factoryOf(::ValidateDeviceSettingsUseCase)
    factoryOf(::LoginUseCase)
    factoryOf(::GetDeductForMonthByStaffUseCase)
}