package com.erabigroup.erabigroupstaffmate

import android.app.Application
import com.erabigroup.erabigroupstaffmate.app.di.core.coreFeaturesModule
import com.erabigroupstaffmate.core.di.coreModule
import com.erabigroup.erabigroupstaffmate.app.di.platform.platformModule
import com.erabigroupstaffmate.syncfromserver.di.syncFromServerModule
import com.erabigroupstaffmate.synctoserver.di.syncToServerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StaffMateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StaffMateApplication)
            modules(
                coreFeaturesModule,
                coreModule,
                platformModule,
                syncFromServerModule,
                syncToServerModule
            )
        }
    }
}