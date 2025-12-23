package com.erabigroupstaffmate.core.di


import com.erabigroupstaffmate.erabitime.data.ErabiDateFormatterImpl
import com.erabigroupstaffmate.erabitime.data.ErabiTimeImpl
import com.erabigroupstaffmate.core.data.fts.DefaultFullTextSearchManager
import com.erabigroupstaffmate.erabitime.domain.ErabiDateFormatter
import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.core.domain.fts.FullTextSearchManager
import com.erabigroupstaffmate.parser.Parser
import com.erabigroupstaffmate.utility.platformdrawer.PlatformDrawer
import com.erabigroupstaffmate.utility.disptachersprovider.DefaultDispatchersProvider
import com.erabigroupstaffmate.utility.uuidprovider.DefaultUuidProvider
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import com.erabigroupstaffmate.utility.resources.files.DefaultResourcesProvider
import com.erabigroupstaffmate.utility.resources.files.ResourcesProvider
import com.erabigroupstaffmate.utility.resources.string.DefaultResourceStringProvider
import com.erabigroupstaffmate.utility.resources.string.ResourceStringProvider
import com.erabigroupstaffmate.utility.uuidprovider.UuidProvider
import com.erabigroupstaffmate.database.di.databaseModule
import com.erabigroupstaffmate.network.di.dataSourceModule
import com.erabigroupstaffmate.preferences.data.DataStoreErabiPreferences
import com.erabigroupstaffmate.preferences.domain.ErabiPreferences
import com.erabigroupstaffmate.utility.validator.email.DefaultEmailAddressValidator
import com.erabigroupstaffmate.utility.validator.email.EmailAddressValidator
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val coreModule = module {
    includes(
        databaseModule,
        dataSourceModule,
        repositoryModule,
        useCaseModule,
    )
    single {
        Parser(
            json = Json {
                explicitNulls = false
                encodeDefaults = true
                ignoreUnknownKeys = true
            }
        )
    }
    single {
        PlatformDrawer(get())
    }


    singleOf(::DefaultEmailAddressValidator).bind(EmailAddressValidator::class)
    singleOf(::DataStoreErabiPreferences).bind(ErabiPreferences::class)
    singleOf(::DefaultFullTextSearchManager).bind(FullTextSearchManager::class)
    singleOf(::ErabiDateFormatterImpl).bind(ErabiDateFormatter::class)
    singleOf(::ErabiTimeImpl).bind(ErabiTime::class)
    singleOf(::DefaultUuidProvider).bind(UuidProvider::class)
    singleOf(::DefaultDispatchersProvider).bind(DispatchersProvider::class)
    singleOf(::DefaultResourcesProvider).bind(ResourcesProvider::class)
    singleOf(::DefaultResourceStringProvider).bind(ResourceStringProvider::class)

}