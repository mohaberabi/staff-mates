package com.erabigroupstaffmate.preferences.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import com.erabigroupstaffmate.preferences.domain.ErabiPreferences
import com.erabigroupstaffmate.preferences.domain.PreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DataStoreErabiPreferences(
    private val dataStore: DataStore<Preferences>,
    private val dispatchers: DispatchersProvider,
) : ErabiPreferences {
    override fun readString(
        key: PreferencesKey,
    ): Flow<String?> = dataStore.data.map {
        it[stringPreferencesKey(key.key)]
    }.flowOn(dispatchers.io)

    override suspend fun writeString(
        key: PreferencesKey,
        value: String
    ) {
        withContext(dispatchers.io) {
            dataStore.edit { it[stringPreferencesKey(key.key)] = value }
        }
    }

    override suspend fun removeString(key: PreferencesKey) {
        withContext(dispatchers.io) {
            dataStore.edit { it.remove(stringPreferencesKey(key.key)) }
        }
    }

    override suspend fun clear() {
        withContext(dispatchers.io) {
            dataStore.edit { it.clear() }
        }
    }
}