enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Interesting feature settings-plugin. Set for all projects
//plugins {
//    id("com.android.settings") version "8.2.2"
//}
//
//android {
//    minSdk = 29
//    compileSdk = 35
//}

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "aphirri-android"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(
    ":app",
    ":core:common",
    ":core:uikit",
    ":core:navigation",
    ":features:home:home-api",
    ":features:home:home-impl",
    ":features:home:home-provider",
    ":features:home:home-ui",
)
include(":features:bottombar")
include(":features:meetings:meetings-api")
include(":features:meetings:meetings-impl")
include(":features:meetings:meetings-provider")
include(":features:meetings:meetings-ui")
