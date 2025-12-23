import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.staffmate.kmp.library)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization)
            implementation(projects.modelhub)
            implementation(projects.erabitime)
            implementation(projects.utility)
            implementation(projects.network)
            implementation(projects.database)
            implementation(projects.preferences)
            implementation(projects.parser)
            implementation(libs.koin.core)
        }
    }

}
