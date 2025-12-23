package com.erabigroupstaffmate.utility.buildconfig

import org.jetbrains.compose.resources.StringResource
import platform.Foundation.NSProcessInfo
import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
actual object AppBuildConfig {
    actual fun isDebug(): Boolean = Platform.isDebugBinary


    actual val storeName: String
        get() = "App store"

}