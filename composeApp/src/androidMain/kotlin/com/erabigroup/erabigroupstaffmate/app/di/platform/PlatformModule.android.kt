package com.erabigroup.erabigroupstaffmate.app.di.platform

import android.content.Context
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.room.Room
import com.erabigroupstaffmate.utility.filelauncher.AndroidInternalFileLauncher
import com.erabigroupstaffmate.database.AppDatabase
import com.erabigroupstaffmate.utility.filelauncher.InternalFileLauncher
import com.erabigroupstaffmate.utility.pdfgenerator.AndroidPdfGenerator
import com.erabigroupstaffmate.utility.pdfgenerator.PdfGenerator
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module = module {

    single {
        PreferenceDataStoreFactory.create {
            get<Context>().dataStoreFile("staffmate.preferences_pb")
        }
    }
    single<AppDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = AppDatabase::class.java,
            name = "core.db"
        ).setQueryCoroutineContext(Dispatchers.IO)
            .fallbackToDestructiveMigration(true)
            .build()
    }
    singleOf(::AndroidInternalFileLauncher).bind(InternalFileLauncher::class)
    singleOf(::AndroidPdfGenerator).bind(PdfGenerator::class)

}