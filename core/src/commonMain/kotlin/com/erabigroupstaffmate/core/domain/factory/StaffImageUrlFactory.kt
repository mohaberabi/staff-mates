package com.erabigroupstaffmate.core.domain.factory

import com.erabigroupstaffmate.utility.constant.STAFF_STORAGE_BUCKET

class StaffImageUrlFactory {


    fun createProfilePic(
        fullName: String,
        id: String,
    ) = createStaffImgPath(fullName = fullName, id = id, img = "pic")

    fun createFrontId(
        fullName: String,
        id: String,
    ) = createStaffImgPath(fullName = fullName, id = id, img = "front")

    fun createBackId(
        fullName: String,
        id: String,
    ) = createStaffImgPath(fullName = fullName, id = id, img = "back")

    private fun createStaffImgPath(
        fullName: String,
        id: String,
        img: String,
    ) = buildString {
        append(STAFF_STORAGE_BUCKET)
        append("/")
        append("${fullName}_${id}")
        append("/")
        append("${img}.jpg")
    }
}
