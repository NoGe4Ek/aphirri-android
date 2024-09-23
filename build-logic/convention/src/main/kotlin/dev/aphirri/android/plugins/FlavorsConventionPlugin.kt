package dev.aphirri.android.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ProductFlavor
import dev.aphirri.android.plugins.common.configureFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class FlavorsConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                configureFlavors<ProductFlavor>()
            }
        }
    }
}