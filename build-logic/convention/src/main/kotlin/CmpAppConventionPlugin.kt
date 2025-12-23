import com.erabipt.convention.common.extensions.getLibrary
import com.erabipt.convention.common.extensions.libs
import com.erabipt.convention.kmp.configureAndroidTarget
import com.erabipt.convention.kmp.configureIosTargets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class CmpAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.staffmate.android.app.compose")
                apply("org.jetbrains.kotlin.multiplatform")
                apply("org.jetbrains.compose")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            configureAndroidTarget()
        }
    }
}