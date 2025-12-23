import com.android.build.api.dsl.ApplicationExtension
import com.erabipt.convention.android.configureKotlinAndroid
import com.erabipt.convention.common.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.io.FileInputStream
import java.util.Properties

class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("com.google.gms.google-services")
            }
            extensions.configure<ApplicationExtension>() {
                namespace = "com.eg.staff.mate"
                defaultConfig {
                    applicationId = "com.eg.staff.mate"
                    versionCode = 1
                    versionName = "1.0.0"
                    targetSdk = libs.findVersion("android-targetSdk").get().toString().toInt()
                }
                configureKotlinAndroid(this)
                buildFeatures { buildConfig = true; }
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        isDebuggable = false
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            file("proguard-rules.pro")
                        )
                    }
                }
            }
        }
    }

}

