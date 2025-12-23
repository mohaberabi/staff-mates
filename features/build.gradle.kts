import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.staffmate.cmp.library)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(projects.modelhub)
            implementation(projects.erabitime)
            implementation(projects.utility)
            implementation(projects.parser)
            implementation(projects.uihub)
            implementation(projects.nfc)
            implementation(projects.core)
            implementation(projects.synctoserver)
            implementation(projects.syncfromserver)
            implementation(projects.calculations)
            implementation(projects.papergen)
        }
    }


}

