package com.erabigroupstaffmate.nfc

import androidx.compose.runtime.Composable
import com.erabigroupstaffmate.nfc.domain.NfcManager
import org.koin.compose.currentKoinScope
import platform.ReplayKit.RPScreenRecorder

@Composable
actual fun rememberNfcManager(): NfcManager {
    val scope = currentKoinScope()
    return IosNfcManager(
        notifier = scope.get(),
        dispatchers = scope.get(),
        delegate = NfcDelegate(
            notifier = scope.get(),
            dispatchers = scope.get()
        )
    )
}
