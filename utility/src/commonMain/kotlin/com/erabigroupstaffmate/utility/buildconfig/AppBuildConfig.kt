package com.erabigroupstaffmate.utility.buildconfig

import org.jetbrains.compose.resources.StringResource

expect object AppBuildConfig {

    fun isDebug(): Boolean

    val storeName: String
}