package com.erabigroupstaffmate.utility.uuidprovider

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid


interface UuidProvider {
    fun generateUuid(): String
}

