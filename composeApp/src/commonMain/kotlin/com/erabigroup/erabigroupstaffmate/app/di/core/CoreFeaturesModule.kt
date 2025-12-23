package com.erabigroup.erabigroupstaffmate.app.di.core

import com.erabigroupstaffmate.features.shared.appmode.viewmodel.AppModeViewModel
import com.erabigroup.erabigroupstaffmate.app.viewmodel.main.MainViewModel
import com.erabigroupstaffmate.calculator.data.DefaultPayrollSummaryCalculator
import com.erabigroupstaffmate.calculator.data.DefaultStaffCalculator
import com.erabigroupstaffmate.calculator.domain.PayrollSummaryCalculator
import com.erabigroupstaffmate.calculator.domain.StaffCalculator
import com.erabigroupstaffmate.core.data.DefaultAuthEventController
import com.erabigroupstaffmate.core.domain.usecase.DeductionsUiMapper
import com.erabigroupstaffmate.core.domain.AuthEventController
import com.erabigroupstaffmate.features.admin.staffdoc.viewmodel.StaffDocViewModel
import com.erabigroupstaffmate.features.kiosk.logshift.viewmodel.LogShiftViewModel
import com.erabigroupstaffmate.features.shared.auth.viewmodel.AuthViewModel
import com.erabigroupstaffmate.features.shared.confirmstaff.viewmodel.ConfirmStaffViewModel
import com.erabigroupstaffmate.features.shared.loghistory.viewmodel.LogHistoryViewModel
import com.erabigroupstaffmate.features.shared.login.viewmodel.LoginViewModel
import com.erabigroupstaffmate.features.shared.readnfc.viewmodel.ReadStaffNfcViewModel
import com.erabigroupstaffmate.features.shared.settings.viewmodel.account.AccountInfoViewModel
import com.erabigroupstaffmate.features.shared.settings.viewmodel.business.BusinessSettingsViewModel
import com.erabigroupstaffmate.features.shared.settings.viewmodel.language.AppLanguageViewModel
import com.erabigroupstaffmate.features.shared.syncer.fromserver.viewmodel.SyncFromServerViewModel
import com.erabigroupstaffmate.features.shared.syncer.toserver.viewmodel.SyncToServerViewModel
import com.erabigroupstaffmate.nfc.data.DefaultStaffNfcCardManager
import com.erabigroupstaffmate.nfc.domain.DefaultNfcNotifier
import com.erabigroupstaffmate.nfc.domain.NfcNotifier
import com.erabigroupstaffmate.nfc.domain.StaffNfcCardManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module


val coreFeaturesModule = module {
    singleOf(::DefaultNfcNotifier).bind(NfcNotifier::class)
    singleOf(::DefaultStaffNfcCardManager).bind(StaffNfcCardManager::class)
    singleOf(::DefaultStaffCalculator).bind(StaffCalculator::class)
    factoryOf(::DeductionsUiMapper)
    singleOf(::DefaultPayrollSummaryCalculator).bind(PayrollSummaryCalculator::class)
    singleOf(::DefaultAuthEventController).bind(AuthEventController::class)
    viewModelOf(::MainViewModel)
    viewModelOf(::AccountInfoViewModel)
    viewModelOf(::ReadStaffNfcViewModel)
    viewModelOf(::ConfirmStaffViewModel)
    viewModelOf(::SyncToServerViewModel)
    viewModelOf(::SyncFromServerViewModel)
    viewModelOf(::LogShiftViewModel)
    viewModelOf(::AuthViewModel)
    viewModelOf(::LogHistoryViewModel)
    viewModelOf(::AppLanguageViewModel)
    viewModelOf(::BusinessSettingsViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::AppModeViewModel)
    viewModelOf(::StaffDocViewModel)
}