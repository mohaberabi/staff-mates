package com.erabigroupstaffmate.network.data

import com.erabigroupstaffmate.network.domain.DeviceSettingsRemoteDataSource
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import com.erabigroupstaffmate.utility.error.ErabiException
import com.erabigroupstaffmate.utility.error.ErrorReason
import com.erabigroupstaffmate.utility.error.ServerErrorReasons
import kotlinx.coroutines.withContext

class FirebaseDeviceSettingsRemoteDataSource(
    private val factory: FirebaseCollectionRefFactory,
    private val dispatchers: DispatchersProvider,
) : DeviceSettingsRemoteDataSource {
    override suspend fun validateDeviceSettings(
        chain: String,
        branch: String
    ) {
        withContext(dispatchers.io) {
            handleFirebaseCall {
                val chainDoc = factory.getChain(chain)
                val chain = chainDoc.get()
                    .takeIf { it.exists }
                    ?: throw ErabiException(reason = ServerErrorReasons.NoChainFound)

                val branch = factory.getBranchForChain(chainDocumentRef = chainDoc, branch = branch)
                    .get()
                    .takeIf { it.exists }
                    ?: throw ErabiException(reason = ServerErrorReasons.NoBranchForChainFound)
            }
        }
    }
}