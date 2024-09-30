import org.gradle.accessors.dm.LibrariesForLibs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val libs = the<LibrariesForLibs>()

plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradle)

    // access LibrariesForLibs in .kt files
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }

    withType(KotlinCompile::class).all {
        // Trigger files to full recompile, cause incremental build not recompile all changes, like
        // manifestPlaceholders updates in Flavor.kt
        val directory = fileTree("src/main/kotlin/dev/aphirri/android/plugins/common") {
            include("**/*")
        }
        inputs.files(directory.files)

        // Remove gradle warning "Couldn't create" some specific tasks, like compileKotlin, compileTestKotlin, etc.
        // cause jvmToolchain not enough
        kotlinOptions {
            jvmTarget = "21"
        }
    }
}

gradlePlugin {
    plugins {
        register("aphirriApplication") {
            id = "aphirri.application"
            implementationClass = "dev.aphirri.android.plugins.ApplicationConventionPlugin"
        }

        register("aphirriFeature") {
            id = "aphirri.feature"
            implementationClass = "dev.aphirri.android.plugins.FeatureConventionPlugin"
        }

        register("aphirriLibrary") {
            id = "aphirri.library"
            implementationClass = "dev.aphirri.android.plugins.LibraryConventionPlugin"
        }

        register("aphirriBuildTypes") {
            id = "aphirri.buildtypes"
            implementationClass = "dev.aphirri.android.plugins.BuildTypesConventionPlugin"
        }

        register("aphirriFlavors") {
            id = "aphirri.flavors"
            implementationClass = "dev.aphirri.android.plugins.FlavorsConventionPlugin"
        }
    }
}