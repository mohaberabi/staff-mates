package com.erabigroupstaffmate.utility.extensions

import kotlinx.coroutines.CancellationException


inline fun <reified T> Result<T>.onFailureNonCancel(
    block: (Throwable) -> Unit
) = onFailure {
    if (it is CancellationException) throw it
    else block(it)
}