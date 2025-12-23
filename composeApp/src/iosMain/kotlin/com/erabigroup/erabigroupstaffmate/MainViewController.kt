package com.erabigroup.erabigroupstaffmate

import androidx.compose.ui.window.ComposeUIViewController
import com.erabigroup.erabigroupstaffmate.app.components.StaffMateComposeApp
import com.erabigroup.erabigroupstaffmate.app.di.core.coreFeaturesModule
import com.erabigroupstaffmate.core.di.coreModule
import com.erabigroup.erabigroupstaffmate.app.di.platform.platformModule
import com.erabigroupstaffmate.syncfromserver.di.syncFromServerModule
import com.erabigroupstaffmate.synctoserver.di.syncToServerModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        startKoin {
            modules(
                coreFeaturesModule,
                coreModule,
                platformModule,
                syncFromServerModule,
                syncToServerModule
            )
        }
    }
) {
    StaffMateComposeApp()
}