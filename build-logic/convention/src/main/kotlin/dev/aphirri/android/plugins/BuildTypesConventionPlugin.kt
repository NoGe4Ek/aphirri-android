package dev.aphirri.android.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import dev.aphirri.android.plugins.common.configureBuildTypes
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class BuildTypesConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                configureBuildTypes<BuildType, ApplicationExtension>()
            }
        }
    }
}