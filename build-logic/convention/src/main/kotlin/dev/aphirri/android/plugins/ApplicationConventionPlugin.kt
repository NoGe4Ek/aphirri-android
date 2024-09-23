package dev.aphirri.android.plugins

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import dev.aphirri.android.Config
import dev.aphirri.android.libs
import dev.aphirri.android.plugins.common.AphirriBuildType
import dev.aphirri.android.plugins.common.AphirriFlavor
import dev.aphirri.android.plugins.common.configureAndroidCompose
import dev.aphirri.android.plugins.common.configureBuildTypes
import dev.aphirri.android.plugins.common.configureFlavors
import dev.aphirri.android.plugins.common.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                configureBuildTypes<ApplicationBuildType> {
                    when (it) {
                        AphirriBuildType.debug -> {
                            isDefault = true
                            // multiDexEnabled = true
                            isDebuggable = true
                        }

                        AphirriBuildType.release -> {
                            isShrinkResources = true
                            isMinifyEnabled = true
                            proguardFiles(
                                getDefaultProguardFile("proguard-android-optimize.txt"),
                                "proguard-rules.pro"
                            )
                        }
                    }
                }
                configureFlavors<ApplicationProductFlavor> {
                    when (it) {
                        AphirriFlavor.stage -> {
                            isDefault = true
                        }

                        AphirriFlavor.prod -> {
                        }
                    }
                }

                defaultConfig.targetSdk = Config.TARGET_SDK
                defaultConfig.applicationId = Config.APPLICATION_ID
                defaultConfig.versionCode = Config.VERSION_CODE
                defaultConfig.versionName = Config.VERSION_NAME

                // Exclude licenses
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }
        }
    }
}

