package dev.aphirri.android.plugins.common

import com.android.build.api.dsl.CommonExtension
import dev.aphirri.android.Config
import dev.aphirri.android.Config.APPLICATION_ID
import dev.aphirri.android.Config.NAME_SPACE_SEPARATOR
import dev.aphirri.android.getModulePath
import dev.aphirri.android.kotlin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.util.removeSuffixIfPresent

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    configureNamespace(commonExtension)
    commonExtension.apply {
        compileSdk = Config.COMPILE_SDK

        defaultConfig {
            minSdk = Config.MIN_SDK

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    configureKotlin()
}

private fun Project.configureNamespace(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    val namespace = APPLICATION_ID + NAME_SPACE_SEPARATOR + path.getModulePath(withSeparator = NAME_SPACE_SEPARATOR)
    commonExtension.namespace = namespace.removeSuffixIfPresent(".app")

}

/**
 * Configure base Kotlin options
 */
private fun Project.configureKotlin() = configure<KotlinAndroidProjectExtension> {
    kotlin {
        jvmToolchain(Config.JDK_VERSION)
    }
    // Override in gradle.properties
    val warningsAsErrors: String? by project
    compilerOptions.apply {
        allWarningsAsErrors.set(warningsAsErrors.toBoolean())
    }
}