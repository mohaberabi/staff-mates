import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    `kotlin-dsl`
}

group = "com.staffmate.convention.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.androidx.room.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("roomConventPlugin") {
            id = "com.staffmate.room.convention"
            implementationClass = "RoomConventionPlugin"
        }
        register("staffmateKmpLibrary") {
            id = "com.staffmate.kmp.library"
            implementationClass = "KmpLibraryConventionPlugin"
        }
        register("staffmateCmpLibrary") {
            id = "com.staffmate.cmp.library"
            implementationClass = "CmpLibraryConventionPlugin"
        }
        register("staffmateAppSign") {
            id = "com.staffmate.android.app.sign"
            implementationClass = "AndroidAppSignConventionPlugin"
        }
        register("staffmateAppFlavors") {
            id = "com.staffmate.android.app.flavors"
            implementationClass = "AndroidAppFlavorConventionPlugin"
        }

        register("staffmateAndroidApp") {
            id = "com.staffmate.android.app"
            implementationClass = "AndroidAppConventionPlugin"
        }
        register("staffmateAndroidAppCompose") {
            id = "com.staffmate.android.app.compose"
            implementationClass = "AndroidAppComposeConventionPlugin"
        }
        register("staffmateCmpApp") {
            id = "com.staffmate.cmp.app"
            implementationClass = "CmpAppConventionPlugin"
        }
    }
}