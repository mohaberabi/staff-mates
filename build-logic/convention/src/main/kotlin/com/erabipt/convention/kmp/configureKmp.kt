package com.erabipt.convention.kmp

import com.android.build.gradle.LibraryExtension
import com.erabipt.convention.common.extensions.pathToNativeFrameWork
import com.erabipt.convention.common.extensions.pathToPackage
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.configureKmp() {
    extensions.configure<LibraryExtension>() {
        namespace = pathToPackage()
    }
    extensions.configure<KotlinMultiplatformExtension>() {
        configureAndroidTarget()
        configureIosTargets(
            frameworkName = pathToNativeFrameWork()
        )

        compilerOptions {
            freeCompilerArgs.add("-Xexpect-actual-classes")
            freeCompilerArgs.add("-opt-in=kotlin.RequireOptIn")
            freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
        }
    }

}