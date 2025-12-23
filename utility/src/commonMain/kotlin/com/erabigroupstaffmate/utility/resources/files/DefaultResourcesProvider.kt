package com.erabigroupstaffmate.utility.resources.files

import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import egroupstaffmate.utility.generated.resources.Res
import kotlinx.coroutines.withContext

class DefaultResourcesProvider(
    private val dispatchers: DispatchersProvider
) : ResourcesProvider {
    override suspend fun provideBytes(path: String): ByteArray {
        return withContext(dispatchers.io) {
            Res.readBytes(path)
        }
    }
}