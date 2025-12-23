//package com.erabigroup.erabigroupstaffmate.hardcoder
//
//import com.erabigroupstaffmate.modelhub.BusinessData
//import com.erabigroupstaffmate.network.data.FirebaseCollectionRefFactory
//import com.erabigroupstaffmate.utility.constant.CommonParams
//import dev.gitlive.firebase.Firebase
//import dev.gitlive.firebase.firestore.FirebaseFirestore
//import dev.gitlive.firebase.firestore.firestore
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.IO
//import kotlinx.coroutines.withContext
//
//class FirebaseHardCoder(
//    private val firestore: FirebaseFirestore = Firebase.firestore
//) {
//
//    private val factory = FirebaseCollectionRefFactory(firestore)
//    private val staffFactory = HardCodedStaffFactory()
//
//    suspend fun addHardCodeAuthKeys(
//        businessData: BusinessData
//    ) {
//        val coll = factory.createChainBranch(
//            chain = businessData.chainId,
//            branch = businessData.branchId,
//            collRef = CommonParams.AUTH_KEYS,
//        )
//        val keys = hardCodedAuthKeys
//        val batch = firestore.batch()
//        withContext(Dispatchers.IO) {
//            keys.forEach { key ->
//                batch.set(coll.document(key.code), key)
//            }
//
//            batch.commit()
//        }
//
//    }
//
//    suspend fun addHardcodedStaff(
//        businessData: BusinessData
//    ) {
//        val coll = factory.createChainBranch(
//            chain = businessData.chainId,
//            branch = businessData.branchId,
//            collRef = CommonParams.STAFF,
//        )
//        val staff = staffFactory.create(businessData = businessData)
//        val batch = firestore.batch()
//        withContext(Dispatchers.IO) {
//            staff.forEach { stf ->
//                batch.set(coll.document(stf.id), stf)
//            }
//            batch.commit()
//        }
//    }
//}