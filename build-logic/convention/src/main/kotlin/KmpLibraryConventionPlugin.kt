import com.android.build.gradle.LibraryExtension
import com.erabipt.convention.android.configureKotlinAndroid
import com.erabipt.convention.common.extensions.commonMainImplementation
import com.erabipt.convention.common.extensions.commonTestImplementation
import com.erabipt.convention.common.extensions.getLibrary
import com.erabipt.convention.common.extensions.libs
import com.erabipt.convention.common.extensions.pathToResPrefix
import com.erabipt.convention.kmp.configureKmp
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


class KmpLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension>() {
                configureKotlinAndroid(this)
                resourcePrefix = this@with.pathToResPrefix()
                experimentalProperties["android.experimental.kmp.enableAndroidResources"] = "true"
                defaultConfig { consumerProguardFile("consumer-rules.pro"); }
            }
            configureKmp()
            dependencies {
                commonMainImplementation(libs.getLibrary("kotlinx-serialization"))
            }
        }
    }
}