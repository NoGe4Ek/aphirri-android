package dev.aphirri.android.plugins

import com.android.build.api.dsl.ProductFlavor
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import dev.aphirri.android.Config.RESOURCES_PREFIX_SEPARATOR
import dev.aphirri.android.androidTestImplementation
import dev.aphirri.android.getModulePath
import dev.aphirri.android.plugins.common.configureFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import dev.aphirri.android.plugins.common.configureAndroidCompose
import dev.aphirri.android.plugins.common.configureKotlinAndroid
import dev.aphirri.android.plugins.common.disableUnnecessaryAndroidTests
import dev.aphirri.android.testImplementation

class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.android.library")
            apply(plugin = "org.jetbrains.kotlin.android")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                configureFlavors<ProductFlavor>()

                // The resource prefix is derived from the module name,
                // so resources inside ":core:module1" must be prefixed with "core_module1_"
                resourcePrefix =
                    path.getModulePath(withSeparator = RESOURCES_PREFIX_SEPARATOR) + RESOURCES_PREFIX_SEPARATOR
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                disableUnnecessaryAndroidTests(target)
            }

            dependencies {
                testImplementation(kotlin("test"))
                androidTestImplementation(kotlin("test"))
            }
        }
    }

}