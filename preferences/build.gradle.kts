plugins {
    alias(libs.plugins.staffmate.kmp.library)

}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
        }
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization)
            api(libs.datastore)
            implementation(projects.utility)

        }
    }
}

