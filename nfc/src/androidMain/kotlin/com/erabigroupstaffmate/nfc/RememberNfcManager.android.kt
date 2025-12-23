package com.erabigroupstaffmate.nfc

import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.erabigroupstaffmate.nfc.domain.NfcManager
import org.koin.compose.currentKoinScope

@Composable
actual fun rememberNfcManager(): NfcManager {
    val scope = currentKoinScope()
    val activity = LocalActivity.current ?: error("could not find the root activity")
    return remember {
        AndroidNfcManager(
            activity = activity,
            dispatchers = scope.get(),
            notifier = scope.get()
        )
    }
}