package com.erabigroupstaffmate.utility.uuidprovider

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class DefaultUuidProvider : UuidProvider {
    @OptIn(ExperimentalUuidApi::class)
    override fun generateUuid(): String = Uuid.random().toString()
}