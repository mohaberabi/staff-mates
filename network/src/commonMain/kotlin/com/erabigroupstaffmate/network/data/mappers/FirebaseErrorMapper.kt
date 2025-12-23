package com.erabigroupstaffmate.network.data.mappers

import com.erabigroupstaffmate.utility.error.ErabiException
import com.erabigroupstaffmate.utility.error.ServerErrorReasons
import dev.gitlive.firebase.FirebaseApiNotAvailableException
import dev.gitlive.firebase.FirebaseException
import dev.gitlive.firebase.FirebaseNetworkException
import dev.gitlive.firebase.FirebaseTooManyRequestsException


fun FirebaseException.toErabiException() = ErabiException(
    reason = toErrorReason(),
    message = message,
    cause = cause
)

internal fun FirebaseException.toErrorReason() = when (this) {
    is FirebaseNetworkException -> ServerErrorReasons.NoNetwork
    is FirebaseTooManyRequestsException -> ServerErrorReasons.TooManyReq
    is FirebaseApiNotAvailableException -> ServerErrorReasons.InternalServerError
    else -> ServerErrorReasons.InternalServerError

}

