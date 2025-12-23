package com.erabigroupstaffmate.network.data

import com.erabigroupstaffmate.modelhub.AuthKeyModel
import com.erabigroupstaffmate.utility.constant.CommonParams
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import com.erabigroupstaffmate.network.domain.AuthKeysRemoteDataSource
import kotlinx.coroutines.withContext

class FirebaseAuthKeysRemoteDataSource(
    private val firestoreFactory: FirebaseCollectionRefFactory,
    private val dispatchers: DispatchersProvider,
) : AuthKeysRemoteDataSource {


    override suspend fun getAllForBranch(
        branch: String,
        chain: String
    ): List<AuthKeyModel> = withContext(dispatchers.io) {
        firestoreFactory.createChainBranch(
            chain = chain,
            branch = branch,
            collRef = CommonParams.AUTH_KEYS
        ).get().documents.map { it.data(AuthKeyModel.serializer()) }
    }
}