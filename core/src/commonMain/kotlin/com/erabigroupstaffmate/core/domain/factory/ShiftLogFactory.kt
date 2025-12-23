package com.erabigroupstaffmate.core.domain.factory

import com.erabigroupstaffmate.utility.constant.getDifferenceInHrs
import com.erabigroupstaffmate.erabitime.domain.ErabiTime
import com.erabigroupstaffmate.modelhub.ShiftLogModel
import com.erabigroupstaffmate.modelhub.StaffModel
import com.erabigroupstaffmate.utility.uuidprovider.UuidProvider

class ShiftLogFactory(
    private val erabiTime: ErabiTime,
    private val uuidProvider: UuidProvider,
) {


    fun createForCheckout(
        previousLog: ShiftLogModel,
    ): ShiftLogModel {

        val loginMillis = previousLog.logInMillis

        val logoutMillis = erabiTime.getCurrentTimeMillisInErabiZone()

        val workedHrs = getDifferenceInHrs(lastMillis = logoutMillis, firstMillis = loginMillis)

        return previousLog.copy(
            logOutMillis = logoutMillis,
            totalWorkedHours = workedHrs
        )
    }


    fun createCheckIn(
        staff: StaffModel,
        businessDate: String,
    ): ShiftLogModel {
        val nowMillis = erabiTime.getCurrentTimeMillisInErabiZone()
        val nowDate = erabiTime.getNowDateTimeInErabiZone()
        return ShiftLogModel(
            id = uuidProvider.generateUuid(),
            staffId = staff.id,
            staffFullName = staff.fullName,
            logYear = nowDate.year.toString(),
            logMonth = nowDate.monthNumber.toString(),
            businessDate = businessDate,
            logInMillis = nowMillis,
            chain = staff.chainId,
            branch = staff.branchId,
        )
    }
}