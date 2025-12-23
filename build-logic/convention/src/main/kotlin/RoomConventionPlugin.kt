import androidx.room.gradle.RoomExtension
import com.erabipt.convention.common.extensions.commonMainApi
import com.erabipt.convention.common.extensions.getLibrary
import com.erabipt.convention.common.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.creating
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate

class RoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("androidx.room")
            }
//            with(configurations) {
//                create("kspAndroid")
//                create("kspIosSimulatorArm64")
//                create("kspIosX64")
//                create("kspIosArm64")
//            }
            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }
            dependencies {
                commonMainApi(libs.getLibrary("room-runtime"))
                commonMainApi(libs.getLibrary("sqlite-bundled"))
                "kspAndroid"(libs.getLibrary("room-compiler"))
                "kspIosSimulatorArm64"(libs.getLibrary("room-compiler"))
                "kspIosX64"(libs.getLibrary("room-compiler"))
                "kspIosArm64"(libs.getLibrary("room-compiler"))
            }
        }
    }
}