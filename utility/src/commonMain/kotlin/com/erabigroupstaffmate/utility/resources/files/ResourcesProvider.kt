package com.erabigroupstaffmate.utility.resources.files

interface ResourcesProvider {
    suspend fun provideBytes(path: String): ByteArray
}

