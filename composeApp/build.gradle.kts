import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.staffmate.cmp.app)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.parser)
            implementation(projects.erabitime)
            implementation(projects.utility)
            implementation(projects.modelhub)
            implementation(projects.core)
            implementation(projects.syncfromserver)
            implementation(projects.synctoserver)
            implementation(projects.uihub)
            implementation(projects.nfc)
            implementation(projects.features)
            implementation(projects.papergen)
            implementation(projects.navigation)
            implementation(projects.database)
            implementation(projects.calculations)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.firebase.firestore)
        }
    }

}

