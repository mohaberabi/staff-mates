package com.erabigroupstaffmate.utility.error

data class ErabiException(
    val reason: ErrorReason,
    override val message: String? = null,
    override val cause: Throwable? = null,
) : Exception(
    message,
    cause
)