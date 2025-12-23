import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.staffmate.cmp.library)
}

kotlin {
    sourceSets {
        iosMain.dependencies {
            api(libs.ktor.client.darwin)
        }
        androidMain.dependencies {
            api(libs.core.splashscreen)
            api(libs.ktor.client.okhttp)
            api(compose.preview)
            api(libs.androidx.activity.compose)
            api(libs.androidx.lifecycle.viewmodel)
            api(libs.androidx.lifecycle.runtimeCompose)
            api(libs.koin.androidx.compose)
        }
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
            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.koin.compose.viewmodel)
            api(libs.coil.compose.core)
            api(libs.coil.compose)
            api(libs.coil.mp)
            api(libs.coil.svg)
            api(libs.coil.network.ktor)
            api(libs.navigation.compose)
            api(libs.bundles.ktor)
        }
    }
}

compose.resources {
    packageOfResClass = "com.erabigroupstaffmate.uihub.resources"
    generateResClass = always
    publicResClass = true

}