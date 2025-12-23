package com.erabigroupstaffmate.network.data

import com.erabigroupstaffmate.utility.constant.CommonParams
import dev.gitlive.firebase.firestore.DocumentReference
import dev.gitlive.firebase.firestore.FirebaseFirestore

class FirebaseCollectionRefFactory(
    private val firebaseFirestore: FirebaseFirestore,
) {
    fun createChainBranch(
        chain: String,
        branch: String,
        collRef: String
    ) = firebaseFirestore.collection(CommonParams.CHAINS)
        .document(chain)
        .collection(CommonParams.BRANCHES)
        .document(branch)
        .collection(collRef)

    fun createForRecord(
        chain: String,
        branch: String,
        collRef: String,
        year: String,
        month: String,
    ) = firebaseFirestore.collection(CommonParams.CHAINS)
        .document(chain)
        .collection(CommonParams.BRANCHES)
        .document(branch)
        .collection(collRef)
        .document(year)
        .collection(month)


    fun getChain(chain: String) = firebaseFirestore
        .collection(CommonParams.CHAINS)
        .document(chain)


    fun getBranchForChain(
        chainDocumentRef: DocumentReference,
        branch: String
    ) = chainDocumentRef.collection(CommonParams.BRANCHES)
        .document(branch)

}