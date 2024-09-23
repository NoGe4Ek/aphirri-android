package dev.aphirri.android.plugins.common

import com.android.build.api.dsl.CommonExtension
import dev.aphirri.android.androidTestImplementation
import dev.aphirri.android.debugImplementation
import dev.aphirri.android.implementation
import dev.aphirri.android.libs
import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeFeatureFlag

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            implementation(platform(libs.androidx.compose.bom))
            androidTestImplementation(platform(libs.androidx.compose.bom))
            implementation(libs.androidx.ui.tooling.preview)
            debugImplementation(libs.androidx.ui.tooling)
        }
    }

    // https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-compiler.html#what-s-next
    extensions.configure<ComposeCompilerGradlePluginExtension> {
        featureFlags = setOf(
            ComposeFeatureFlag.OptimizeNonSkippingGroups
        )

        // Report info about composable functions and their recomposition
        reportsDestination = layout.buildDirectory.dir("report_compose_compiler")
        // The metrics show which composable functions are skippable, restartable, read-only, and so on.
        metricsDestination = layout.buildDirectory.dir("report_compose_compiler")
        // This allows for considering classes you not control as stable.
        stabilityConfigurationFiles =
            listOf(rootProject.layout.projectDirectory.file("compose_compiler_stability_config.conf"))

        fun Provider<String>.onlyIfTrue() = flatMap { provider { it.takeIf(String::toBoolean) } }
        fun Provider<*>.relativeToRootProject(dir: String) = flatMap {
            rootProject.layout.buildDirectory.dir(projectDir.toRelativeString(rootDir))
        }.map { it.dir(dir) }

        // We can turn on/off metrics collecting. See gradle.properties
        project.providers.gradleProperty("enableComposeCompilerMetrics").onlyIfTrue()
            .relativeToRootProject("compose-metrics")
            .let(metricsDestination::set)

        project.providers.gradleProperty("enableComposeCompilerReports").onlyIfTrue()
            .relativeToRootProject("compose-reports")
            .let(reportsDestination::set)
    }
}