package com.erabigroupstaffmate.network.data.mappers

import com.erabigroupstaffmate.utility.error.ErabiException
import com.erabigroupstaffmate.utility.error.ServerErrorReasons
import dev.gitlive.firebase.auth.FirebaseAuthActionCodeException
import dev.gitlive.firebase.auth.FirebaseAuthEmailException
import dev.gitlive.firebase.auth.FirebaseAuthException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidUserException
import dev.gitlive.firebase.auth.FirebaseAuthMultiFactorException
import dev.gitlive.firebase.auth.FirebaseAuthRecentLoginRequiredException
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import dev.gitlive.firebase.auth.FirebaseAuthWeakPasswordException


internal fun FirebaseAuthException.toServerErrorReason() = when (this) {
    is FirebaseAuthActionCodeException -> ServerErrorReasons.InvalidArgs
    is FirebaseAuthEmailException -> ServerErrorReasons.InvalidEmail
    is FirebaseAuthInvalidCredentialsException -> ServerErrorReasons.InvalidEmail
    is FirebaseAuthWeakPasswordException -> ServerErrorReasons.InvalidEmail
    is FirebaseAuthInvalidUserException -> ServerErrorReasons.InvalidEmail
    is FirebaseAuthMultiFactorException -> ServerErrorReasons.InvalidEmail
    is FirebaseAuthRecentLoginRequiredException -> ServerErrorReasons.InvalidEmail
    is FirebaseAuthUserCollisionException -> ServerErrorReasons.InvalidEmail
    else -> ServerErrorReasons.InternalServerError

}


fun FirebaseAuthException.toErabiException() = ErabiException(
    reason = toServerErrorReason(),
    message = message,
    cause = cause
)
