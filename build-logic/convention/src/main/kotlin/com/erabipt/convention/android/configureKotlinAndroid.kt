package com.erabipt.convention.android

import com.android.build.api.dsl.CommonExtension
import com.erabipt.convention.common.extensions.libs
import com.erabipt.convention.kt.configureKotlin
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureKotlinAndroid(
    commonExtensions: CommonExtension<*, *, *, *, *, *>
) {
    with(commonExtensions) {
        compileSdk = libs.findVersion("android-compileSdk").get().toString().toInt()
        defaultConfig.minSdk = libs.findVersion("android-minSdk").get().toString().toInt()
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" }; }
        configureKotlin()
    }
}
