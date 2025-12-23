package com.erabigroupstaffmate.network.data

import com.erabigroupstaffmate.modelhub.PayrollPayload
import com.erabigroupstaffmate.modelhub.StaffBorrowModel
import com.erabigroupstaffmate.utility.constant.CommonParams
import com.erabigroupstaffmate.utility.disptachersprovider.DispatchersProvider
import com.erabigroupstaffmate.network.domain.StaffBorrowRemoteDataSource
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.withContext

class FirebaseBorrowRemoteDataSource(
    private val firestore: FirebaseFirestore,
    private val dispatchers: DispatchersProvider,
    private val firestoreFactory: FirebaseCollectionRefFactory
) : StaffBorrowRemoteDataSource {
    override suspend fun addBorrow(borrow: StaffBorrowModel) {
        withContext(dispatchers.io) {
            firestoreFactory.createForRecord(
                chain = borrow.chain,
                branch = borrow.branch,
                collRef = CommonParams.BORROW,
                year = borrow.recordYear,
                month = borrow.recordMonth
            ).document(borrow.id).set(borrow)
        }
    }

    override suspend fun addAll(borrows: List<StaffBorrowModel>) {
        val batch = firestore.batch()
        borrows.forEach { borrow ->
            val doc = firestoreFactory.createForRecord(
                chain = borrow.chain,
                branch = borrow.branch,
                collRef = CommonParams.BORROW,
                year = borrow.recordYear,
                month = borrow.recordMonth
            ).document(borrow.id)
            batch.set(doc, borrow)
        }
        batch.commit()
    }

    override suspend fun getAllByYearMonth(
        payload: PayrollPayload,
    ): List<StaffBorrowModel> {
        return withContext(dispatchers.io) {
            firestoreFactory.createForRecord(
                chain = payload.chainId,
                branch = payload.branchId,
                collRef = CommonParams.BORROW,
                year = payload.year,
                month = payload.month
            ).get().documents.map { it.data(StaffBorrowModel.serializer()) }
        }
    }
}