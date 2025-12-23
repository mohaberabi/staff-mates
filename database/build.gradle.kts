import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.staffmate.kmp.library)
    alias(libs.plugins.staffmate.room)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization)
            implementation(projects.modelhub)
            implementation(projects.erabitime)
            implementation(projects.utility)
            api(libs.room.runtime)
            api(libs.datastore)
            implementation(libs.sqlite.bundled)
            implementation(libs.koin.core)
        }
    }
}

