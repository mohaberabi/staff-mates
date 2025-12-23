package com.erabigroupstaffmate.network.data

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.ShiftLogModel
import com.erabigroupstaffmate.utility.constant.CommonParams
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import com.erabigroupstaffmate.network.domain.StaffShiftLogRemoteDataSource
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.withContext

class FirebaseShiftLogRemoteDataSource(
    private val firestore: FirebaseFirestore,
    private val dispatchers: DispatchersProvider,
    private val firestoreFactory: FirebaseCollectionRefFactory
) : StaffShiftLogRemoteDataSource {
    override suspend fun addLog(
        log: ShiftLogModel,
    ) {
        withContext(dispatchers.io) {
            firestoreFactory.createForRecord(
                chain = log.chain,
                branch = log.branch,
                collRef = CommonParams.SHIFT_LOG,
                year = log.logYear,
                month = log.logMonth,
            ).document(log.id).set(log)
        }
    }

    override suspend fun addAll(logs: List<ShiftLogModel>) {
        val batch = firestore.batch()
        logs.forEach { log ->
            val doc = firestoreFactory.createForRecord(
                chain = log.chain,
                branch = log.branch,
                collRef = CommonParams.SHIFT_LOG,
                year = log.logYear,
                month = log.logMonth,
            ).document(log.id)
            batch.set(doc, log)
        }
        batch.commit()
    }

    override suspend fun getAll(
        payload: PayrollPayload,
    ): List<ShiftLogModel> {
        return withContext(dispatchers.io) {
            firestoreFactory.createForRecord(
                chain = payload.chainId,
                branch = payload.branchId,
                collRef = CommonParams.SHIFT_LOG,
                year = payload.year,
                month = payload.month,
            ).get().documents.map { it.data(ShiftLogModel.serializer()) }
        }
    }
}