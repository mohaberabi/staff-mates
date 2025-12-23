package com.erabigroupstaffmate.utility.resources.string

import org.jetbrains.compose.resources.StringResource

interface ResourceStringProvider {
    suspend fun provideString(resource: StringResource, vararg formats: Any): String
}