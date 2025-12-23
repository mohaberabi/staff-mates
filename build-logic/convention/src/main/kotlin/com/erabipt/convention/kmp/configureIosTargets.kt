package com.erabipt.convention.kmp

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureIosTargets(
    static: Boolean = true,
    frameworkName: String
) {
    extensions.configure<KotlinMultiplatformExtension>() {
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { iosTarget ->
            iosTarget.binaries.framework {
                baseName = frameworkName
                isStatic = static
            }
        }
    }
}