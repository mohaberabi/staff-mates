package com.erabigroupstaffmate.network.data

import com.erabigroupstaffmate.network.data.mappers.toErabiException
import dev.gitlive.firebase.FirebaseException
import dev.gitlive.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.CancellationException


inline fun <T> handleFirebaseCall(block: () -> T): T {
    return try {
        block()
    } catch (e: FirebaseAuthException) {
        e.printStackTrace()
        throw e.toErabiException()
    } catch (e: FirebaseException) {
        e.printStackTrace()
        throw e.toErabiException()
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        throw e
    }
}