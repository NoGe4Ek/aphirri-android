package dev.aphirri.android.plugins.common

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Project

/**
 * Disable unnecessary Android instrumented tests for the [project] if there is no `androidTest` folder.
 * Otherwise, these projects would be compiled, packaged, installed and ran only to end-up with the following message:
 *
 * > Starting 0 tests on AVD
 *
 * Note: this could be improved by checking other potential sourceSets based on buildTypes and flavors.
 */
internal fun LibraryAndroidComponentsExtension.disableUnnecessaryAndroidTests(
    project: Project,
) = beforeVariants { variant ->

    if (variant.buildType == AphirriBuildType.release.name) {
        // Also disable capability to run composable preview on emulator with error: "Cannot obtain the package"
        variant.enableAndroidTest = variant.enableAndroidTest
                && project.projectDir.resolve("src/androidTest").exists()
    }
}