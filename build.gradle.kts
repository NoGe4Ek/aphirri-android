// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    // AGP
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false

    // Replace legacy plugin application org.jetbrains.kotlin:kotlin-gradle-plugin by using plugins DSL
    // https://plugins.gradle.org/plugin/org.jetbrains.kotlin.android
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false

    // Compose compiler kotlin 2.0+
    alias(libs.plugins.compose.compiler) apply false
}
