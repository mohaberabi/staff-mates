package com.erabigroupstaffmate.uihub.uimodel


import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.borrow_reason_education
import com.erabigroupstaffmate.uihub.resources.borrow_reason_emergency
import com.erabigroupstaffmate.uihub.resources.borrow_reason_family
import com.erabigroupstaffmate.uihub.resources.borrow_reason_health
import com.erabigroupstaffmate.uihub.resources.borrow_reason_other
import com.erabigroupstaffmate.uihub.resources.borrow_reason_transport
import com.erabigroupstaffmate.uihub.resources.borrow_reason_travel
import org.jetbrains.compose.resources.StringResource

enum class BorrowReason(
    val stringRes: StringResource
) {
    Emergency(Res.string.borrow_reason_emergency),
    Family(Res.string.borrow_reason_family),
    Health(Res.string.borrow_reason_health),
    Travel(Res.string.borrow_reason_travel),
    Transport(Res.string.borrow_reason_transport),
    Education(Res.string.borrow_reason_education),
    Other(Res.string.borrow_reason_other)
}