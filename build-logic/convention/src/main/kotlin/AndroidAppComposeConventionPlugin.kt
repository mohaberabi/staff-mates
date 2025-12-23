import com.android.build.api.dsl.ApplicationExtension
import com.erabipt.convention.android.configureComposeAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidAppComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.staffmate.android.app")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
        }
    }
}