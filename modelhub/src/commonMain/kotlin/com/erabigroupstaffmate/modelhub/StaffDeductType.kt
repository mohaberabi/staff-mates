package com.erabigroupstaffmate.modelhub

import kotlinx.serialization.Serializable


@Serializable
enum class StaffDeductType {
    Deduct,
    Borrow;

    companion object {
        fun fromString(string: String) =
            runCatching {
                enumValueOf<StaffDeductType>(string)
            }.getOrDefault(Borrow)
    }
}