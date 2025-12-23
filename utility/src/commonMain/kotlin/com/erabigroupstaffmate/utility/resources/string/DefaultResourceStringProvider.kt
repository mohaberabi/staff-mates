package com.erabigroupstaffmate.utility.resources.string

import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

class DefaultResourceStringProvider : ResourceStringProvider {
    override suspend fun provideString(resource: StringResource, vararg formats: Any): String {
        return getString(resource = resource, formatArgs = formats)
    }
}