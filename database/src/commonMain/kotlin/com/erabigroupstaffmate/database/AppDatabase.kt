package com.erabigroupstaffmate.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.erabigroupstaffmate.database.dao.AuthKeyDao
import com.erabigroupstaffmate.database.dao.ShiftLogDao
import com.erabigroupstaffmate.database.dao.StaffBorrowDao
import com.erabigroupstaffmate.database.dao.StaffDao
import com.erabigroupstaffmate.database.dao.StaffDeductDao
import com.erabigroupstaffmate.database.dao.fts.StaffFtsDao
import com.erabigroupstaffmate.database.entity.AuthKeyEntity
import com.erabigroupstaffmate.database.entity.ShiftLogEntity
import com.erabigroupstaffmate.database.entity.StaffBorrowEntity
import com.erabigroupstaffmate.database.entity.StaffDeductEntity
import com.erabigroupstaffmate.database.entity.StaffEntity
import com.erabigroupstaffmate.database.entity.StaffFtsEntity

@Database(
    entities = [
        StaffFtsEntity::class,
        StaffEntity::class,
        StaffDeductEntity::class,
        StaffBorrowEntity::class,
        ShiftLogEntity::class,
        AuthKeyEntity::class,
    ],
    version = 1
)
@ConstructedBy(AppDatabaseCreator::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun logDao(): ShiftLogDao

    abstract fun staffDao(): StaffDao
    abstract fun borrowDao(): StaffBorrowDao
    abstract fun deductDao(): StaffDeductDao
    abstract fun staffFtsDao(): StaffFtsDao

    abstract fun authDao(): AuthKeyDao

}

@Suppress(
    "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING",
    "NO_ACTUAL_FOR_EXPECT"
)
expect object AppDatabaseCreator : RoomDatabaseConstructor<AppDatabase>