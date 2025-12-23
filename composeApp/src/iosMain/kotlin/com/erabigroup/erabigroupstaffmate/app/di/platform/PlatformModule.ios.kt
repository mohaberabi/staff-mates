package com.erabigroup.erabigroupstaffmate.app.di.platform

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

import com.erabigroupstaffmate.database.AppDatabase
import com.erabigroupstaffmate.utility.localizations.IosInternalFileLauncher
import com.erabigroupstaffmate.utility.filelauncher.InternalFileLauncher
import com.erabigroupstaffmate.utility.pdfgenerator.IosPdfGenerator
import com.erabigroupstaffmate.utility.pdfgenerator.PdfGenerator
import com.erabigroupstaffmate.utility.utils.file.getDocumentDirectory
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import okio.Path.Companion.toPath
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import platform.Foundation.NSHomeDirectory

actual val platformModule: Module = module {
    single { createIosRoom() }
    single { createIosDataStore() }
    singleOf(::IosInternalFileLauncher).bind(InternalFileLauncher::class)
    singleOf(::IosPdfGenerator).bind(PdfGenerator::class)

}

@OptIn(ExperimentalForeignApi::class)
internal fun createIosRoom(): AppDatabase {
    val documentsDirectory = getDocumentDirectory()?.path ?: NSHomeDirectory()
    val dbFilePath = "$documentsDirectory/core.db"
    val builder = Room.databaseBuilder<AppDatabase>(
        name = dbFilePath,
    ).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
    return builder.build()
}


@OptIn(ExperimentalForeignApi::class)
fun createIosDataStore(): DataStore<Preferences> {
    val docsDir = getDocumentDirectory()
    val root = requireNotNull(docsDir).path
    return PreferenceDataStoreFactory.createWithPath {
        ("$root/staffmate.preferences_pb").toPath()
    }
}