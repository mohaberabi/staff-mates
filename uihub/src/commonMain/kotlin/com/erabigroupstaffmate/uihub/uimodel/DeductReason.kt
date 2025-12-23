package com.erabigroupstaffmate.uihub.uimodel


import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.deduct_reason_absent
import com.erabigroupstaffmate.uihub.resources.deduct_reason_arrive_late
import com.erabigroupstaffmate.uihub.resources.deduct_reason_bad_behaviour
import com.erabigroupstaffmate.uihub.resources.deduct_reason_bad_performance
import com.erabigroupstaffmate.uihub.resources.deduct_reason_break_rules
import com.erabigroupstaffmate.uihub.resources.deduct_reason_damage
import com.erabigroupstaffmate.uihub.resources.deduct_reason_leave_early
import com.erabigroupstaffmate.uihub.resources.deduct_reason_other
import org.jetbrains.compose.resources.StringResource


enum class DeductReason(
    val stringRes: StringResource
) {
    ArriveLate(Res.string.deduct_reason_arrive_late),
    Absent(Res.string.deduct_reason_absent),
    LeaveEarly(Res.string.deduct_reason_leave_early),
    BreakRules(Res.string.deduct_reason_break_rules),
    BadPerformance(Res.string.deduct_reason_bad_performance),
    BadBehaviour(Res.string.deduct_reason_bad_behaviour),
    Damage(Res.string.deduct_reason_damage),
    Other(Res.string.deduct_reason_other)
}