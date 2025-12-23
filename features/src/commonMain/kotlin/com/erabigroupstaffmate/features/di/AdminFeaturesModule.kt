package com.erabigroupstaffmate.features.di


import com.erabigroupstaffmate.features.admin.adddeduct.viewmodel.AddDeductViewModel
import com.erabigroupstaffmate.features.admin.deductions.viewmodel.DeductionsViewModel
import com.erabigroupstaffmate.features.admin.home.viewmdoel.AdminHomeViewModel
import com.erabigroupstaffmate.features.admin.payroll.viewmodel.PayrollViewModel
import com.erabigroupstaffmate.features.admin.setup.viewmodel.writenfc.WriteStaffToNfcViewModel
import com.erabigroupstaffmate.features.admin.staff.viewmodel.BranchStaffViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val adminFeaturesModule = module {
    viewModelOf(::PayrollViewModel)
    viewModelOf(::WriteStaffToNfcViewModel)
    viewModelOf(::BranchStaffViewModel)
    viewModelOf(::DeductionsViewModel)
    viewModelOf(::AddDeductViewModel)
    viewModelOf(::AdminHomeViewModel)
}