package com.erabigroupstaffmate.network.data

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.StaffDeductModel
import com.erabigroupstaffmate.utility.constant.CommonParams
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import com.erabigroupstaffmate.network.domain.StaffDeductRemoteDataSource
import dev.gitlive.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.withContext

class FirebaseDeductRemoteDataSource(
    private val firestore: FirebaseFirestore,
    private val dispatchers: DispatchersProvider,
    private val firestoreFactory: FirebaseCollectionRefFactory
) : StaffDeductRemoteDataSource {

    override suspend fun addDeduct(deduct: StaffDeductModel) {
        withContext(dispatchers.io) {
            firestoreFactory.createForRecord(
                chain = deduct.chain,
                branch = deduct.branch,
                collRef = CommonParams.DEDUCT,
                year = deduct.recordYear,
                month = deduct.recordMonth,
            ).document(deduct.id).set(deduct)
        }
    }

    override suspend fun addAll(deducts: List<StaffDeductModel>) {
        val batch = firestore.batch()
        deducts.forEach { deduct ->
            val doc = firestoreFactory.createForRecord(
                chain = deduct.chain,
                branch = deduct.branch,
                collRef = CommonParams.DEDUCT,
                year = deduct.recordYear,
                month = deduct.recordMonth,
            ).document(deduct.id)
            batch.set(doc, deduct)
        }
        batch.commit()
    }

    override suspend fun getAllByYearMonth(
        payload: PayrollPayload,
    ): List<StaffDeductModel> {
        return withContext(dispatchers.io) {
            firestoreFactory.createForRecord(
                chain = payload.chainId,
                branch = payload.branchId,
                collRef = CommonParams.DEDUCT,
                year = payload.year,
                month = payload.month,
            ).get().documents.map { it.data(StaffDeductModel.serializer()) }
        }
    }
}