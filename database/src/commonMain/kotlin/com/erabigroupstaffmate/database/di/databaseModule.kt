package com.erabigroupstaffmate.database.di


import com.erabigroupstaffmate.database.AppDatabase
import com.erabigroupstaffmate.database.dao.AuthKeyDao
import com.erabigroupstaffmate.database.dao.ShiftLogDao
import com.erabigroupstaffmate.database.dao.StaffBorrowDao
import com.erabigroupstaffmate.database.dao.StaffDeductDao
import com.erabigroupstaffmate.database.dao.fts.StaffFtsDao
import org.koin.dsl.module


val databaseModule = module {
    single<StaffDeductDao> {
        get<AppDatabase>().deductDao()
    }
    single<StaffBorrowDao> {
        get<AppDatabase>().borrowDao()
    }
    single<StaffFtsDao> {
        get<AppDatabase>().staffFtsDao()
    }
    single<AuthKeyDao> {
        get<AppDatabase>().authDao()
    }
    single {
        get<AppDatabase>().staffDao()

    }
    single<ShiftLogDao> {
        get<AppDatabase>().logDao()
    }
}