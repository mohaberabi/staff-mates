package com.erabigroupstaffmate.uihub.uimodel.extensions

import com.erabigroupstaffmate.uihub.resources.Res
import com.erabigroupstaffmate.uihub.resources.error
import com.erabigroupstaffmate.uihub.resources.error_bad_certificate
import com.erabigroupstaffmate.uihub.resources.error_bad_request
import com.erabigroupstaffmate.uihub.resources.error_bad_response
import com.erabigroupstaffmate.uihub.resources.error_canceled
import com.erabigroupstaffmate.uihub.resources.error_conflict
import com.erabigroupstaffmate.uihub.resources.error_email_in_use
import com.erabigroupstaffmate.uihub.resources.error_email_required
import com.erabigroupstaffmate.uihub.resources.error_forbidden
import com.erabigroupstaffmate.uihub.resources.error_internal_server
import com.erabigroupstaffmate.uihub.resources.error_invalid_args
import com.erabigroupstaffmate.uihub.resources.error_invalid_email
import com.erabigroupstaffmate.uihub.resources.error_no_branch_chain
import com.erabigroupstaffmate.uihub.resources.error_no_chain
import com.erabigroupstaffmate.uihub.resources.error_no_network
import com.erabigroupstaffmate.uihub.resources.error_not_found
import com.erabigroupstaffmate.uihub.resources.error_permission_denied
import com.erabigroupstaffmate.uihub.resources.error_timeout
import com.erabigroupstaffmate.uihub.resources.error_too_many_req
import com.erabigroupstaffmate.uihub.resources.error_unauthorized
import com.erabigroupstaffmate.uihub.resources.error_unavailable
import com.erabigroupstaffmate.uihub.resources.error_unknown
import com.erabigroupstaffmate.uihub.resources.error_weak_password
import com.erabigroupstaffmate.uihub.resources.error_wrong_pass
import com.erabigroupstaffmate.utility.error.ErabiException
import com.erabigroupstaffmate.utility.error.ServerErrorReasons
import org.jetbrains.compose.resources.getString


suspend fun Throwable.stringMessage() = getString(stringRes())

fun Throwable.stringRes() = when (this) {
    is ErabiException -> this.getStringRes()
    else -> Res.string.error
}


internal fun ErabiException.getStringRes() = when (this.reason) {
    ServerErrorReasons.NoNetwork -> Res.string.error_no_network
    ServerErrorReasons.UnknownError -> Res.string.error_unknown
    ServerErrorReasons.EmailInUse -> Res.string.error_email_in_use
    ServerErrorReasons.WrongPass -> Res.string.error_wrong_pass
    ServerErrorReasons.TooManyReq -> Res.string.error_too_many_req
    ServerErrorReasons.InvalidEmail -> Res.string.error_invalid_email
    ServerErrorReasons.WeakPassword -> Res.string.error_weak_password
    ServerErrorReasons.EmailReq -> Res.string.error_email_required
    ServerErrorReasons.Canceled -> Res.string.error_canceled
    ServerErrorReasons.Timeout -> Res.string.error_timeout
    ServerErrorReasons.InvalidArgs -> Res.string.error_invalid_args
    ServerErrorReasons.PermissionDenied -> Res.string.error_permission_denied
    ServerErrorReasons.Unavailable -> Res.string.error_unavailable
    ServerErrorReasons.NotFound -> Res.string.error_not_found
    ServerErrorReasons.BadCertificate -> Res.string.error_bad_certificate
    ServerErrorReasons.BadResponse -> Res.string.error_bad_response
    ServerErrorReasons.BadRequest -> Res.string.error_bad_request
    ServerErrorReasons.UnAuthorised -> Res.string.error_unauthorized
    ServerErrorReasons.Forbidden -> Res.string.error_forbidden
    ServerErrorReasons.Conflict -> Res.string.error_conflict
    ServerErrorReasons.InternalServerError -> Res.string.error_internal_server
    ServerErrorReasons.NoChainFound -> Res.string.error_no_chain
    ServerErrorReasons.NoBranchForChainFound -> Res.string.error_no_branch_chain
}