package dev.aphirri.android.plugins

import dev.aphirri.android.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("aphirri.library")
            }

            dependencies {
                implementation(project(":core:uikit"))
                implementation(project(":core:common"))
            }
        }
    }
}