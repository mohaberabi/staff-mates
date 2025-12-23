package com.erabipt.convention.android

import com.erabipt.convention.common.extensions.getLibrary
import com.erabipt.convention.common.extensions.libs
import com.erabipt.convention.common.`typealias`.DefaultCommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureComposeAndroid(
    commonExtension: DefaultCommonExtension
) {
    with(commonExtension) {
        buildFeatures { compose = true; }
        dependencies {
            val bom = libs.getLibrary("androidx.compose.bom")
            "implementation"(platform(bom))
            "testImplementation"(platform(bom))
            "debugImplementation"(libs.getLibrary("androidx-compose-ui-tooling.preview"))
            "debugImplementation"(libs.getLibrary("androidx-compose-ui-tooling"))
        }
    }
}