import org.gradle.accessors.dm.LibrariesForLibs

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