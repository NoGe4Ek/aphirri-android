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

include(":app")

include(":core:common")
include(":core:uikit")
include(":core:navigation")

include(":features:bottombar:bottombar-ui")

include(":features:home:home-api")
include(":features:home:home-impl")
include(":features:home:home-provider")
include(":features:home:home-ui")

include(":features:meetings:meetings-api")
include(":features:meetings:meetings-impl")
include(":features:meetings:meetings-provider")
include(":features:meetings:meetings-ui")

include(":features:auth:auth-api")
include(":features:auth:auth-impl")
include(":features:auth:auth-provider")
include(":features:auth:auth-ui")

include(":features:services:services-ui")

include(":features:storage:storage-ui")
