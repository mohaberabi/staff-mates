package com.erabigroupstaffmate.papergen.data.utils

import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.base_salary
import com.erabigroupstaffmate.uihub.resources.borrowings
import com.erabigroupstaffmate.uihub.resources.branch
import com.erabigroupstaffmate.uihub.resources.chain
import com.erabigroupstaffmate.uihub.resources.deductions
import com.erabigroupstaffmate.uihub.resources.earn_per_hr
import com.erabigroupstaffmate.uihub.resources.growth_earn
import com.erabigroupstaffmate.uihub.resources.hrs_per_shift
import com.erabigroupstaffmate.uihub.resources.net_earn
import com.erabigroupstaffmate.uihub.resources.off_days
import com.erabigroupstaffmate.utility.resources.string.ResourceStringProvider


data class PayrollGenStrings(
    val baseSalary: String,
    val offDays: String,
    val hrsPerShift: String,
    val borrowing: String,
    val deductions: String,
    val growthEarn: String,
    val netEarn: String,
    val earnPerHr: String,
    val chain: String,
    val branch: String,
)

suspend fun ResourceStringProvider.providePayroll() = PayrollGenStrings(
    baseSalary = provideString(Res.string.base_salary),
    offDays = provideString(Res.string.off_days),
    hrsPerShift = provideString(Res.string.hrs_per_shift),
    borrowing = provideString(Res.string.borrowings),
    deductions = provideString(Res.string.deductions),
    growthEarn = provideString(Res.string.growth_earn),
    netEarn = provideString(Res.string.net_earn),
    earnPerHr = provideString(Res.string.earn_per_hr),
    chain = provideString(Res.string.chain),
    branch = provideString(Res.string.branch)
)