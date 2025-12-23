package com.erabigroupstaffmate.database.mappers

import com.erabigroupstaffmate.database.entity.AuthKeyEntity
import com.erabigroupstaffmate.modelhub.AuthKeyModel
import com.erabigroupstaffmate.modelhub.AuthRole


fun AuthKeyModel.toEntity() = AuthKeyEntity(
    id = code,
    name = name,
    isAdmin = isAdmin,
    roles = roles.joinToString(",") { "$it" }
)


fun AuthKeyEntity.toModel() = AuthKeyModel(
    code = id,
    name = name,
    isAdmin = isAdmin,
    roles = roles.split(",").map { it.toIntOrNull() ?: AuthRole.AccessStaff.type }
)