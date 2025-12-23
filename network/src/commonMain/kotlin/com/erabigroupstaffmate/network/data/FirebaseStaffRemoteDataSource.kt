package com.erabigroupstaffmate.network.data

import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.utility.constant.CommonParams
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import com.erabigroupstaffmate.network.domain.StaffRemoteDataSource
import kotlinx.coroutines.withContext

class FirebaseStaffRemoteDataSource(
    private val dispatchers: DispatchersProvider,
    private val firestoreFactory: FirebaseCollectionRefFactory
) : StaffRemoteDataSource {
    override suspend fun getAllStaff(
        branch: String,
        chain: String
    ): List<StaffModel> {
        return withContext(dispatchers.io) {
            firestoreFactory.createChainBranch(
                chain = chain,
                branch = branch,
                collRef = CommonParams.STAFF
            ).get().documents.map { it.data(StaffModel.serializer()) }
        }
    }
}