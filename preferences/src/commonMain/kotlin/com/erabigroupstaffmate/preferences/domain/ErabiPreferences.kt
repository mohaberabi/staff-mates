package com.erabigroupstaffmate.preferences.domain

import kotlinx.coroutines.flow.Flow

interface ErabiPreferences {
    fun readString(key: PreferencesKey): Flow<String?>
    suspend fun writeString(key: PreferencesKey, value: String)
    suspend fun removeString(key: PreferencesKey)
    suspend fun clear()
}