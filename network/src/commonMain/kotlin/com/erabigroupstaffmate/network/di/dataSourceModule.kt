package com.erabigroupstaffmate.network.di


import com.erabigroupstaffmate.network.data.AuthRemoteSourceImpl
import com.erabigroupstaffmate.network.data.FirebaseAuthKeysRemoteDataSource
import com.erabigroupstaffmate.network.data.FirebaseBorrowRemoteDataSource
import com.erabigroupstaffmate.network.data.FirebaseCollectionRefFactory
import com.erabigroupstaffmate.network.data.FirebaseDeductRemoteDataSource
import com.erabigroupstaffmate.network.data.FirebaseDeviceSettingsRemoteDataSource
import com.erabigroupstaffmate.network.data.FirebaseShiftLogRemoteDataSource
import com.erabigroupstaffmate.network.data.FirebaseStaffRemoteDataSource
import com.erabigroupstaffmate.network.domain.AuthKeysRemoteDataSource
import com.erabigroupstaffmate.network.domain.AuthRemoteDataSource
import com.erabigroupstaffmate.network.domain.DeviceSettingsRemoteDataSource
import com.erabigroupstaffmate.network.domain.StaffBorrowRemoteDataSource
import com.erabigroupstaffmate.network.domain.StaffDeductRemoteDataSource
import com.erabigroupstaffmate.network.domain.StaffRemoteDataSource
import com.erabigroupstaffmate.network.domain.StaffShiftLogRemoteDataSource
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val dataSourceModule = module {

    single {
        Firebase.firestore
    }
    single {
        Firebase.auth
    }
    factoryOf(::FirebaseCollectionRefFactory)
    singleOf(::FirebaseShiftLogRemoteDataSource).bind(StaffShiftLogRemoteDataSource::class)
    singleOf(::FirebaseStaffRemoteDataSource).bind(StaffRemoteDataSource::class)
    singleOf(::FirebaseAuthKeysRemoteDataSource).bind(AuthKeysRemoteDataSource::class)
    singleOf(::FirebaseBorrowRemoteDataSource).bind(StaffBorrowRemoteDataSource::class)
    singleOf(::FirebaseDeductRemoteDataSource).bind(StaffDeductRemoteDataSource::class)
    singleOf(::FirebaseDeviceSettingsRemoteDataSource).bind(DeviceSettingsRemoteDataSource::class)
    singleOf(::AuthRemoteSourceImpl).bind(AuthRemoteDataSource::class)

}