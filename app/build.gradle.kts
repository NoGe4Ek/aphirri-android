plugins {
    alias(libs.plugins.aphirri.application)
    alias(libs.plugins.kotlin.serialization)
}

android {
    defaultConfig {
        signingConfig = signingConfigs.getByName("debug")
    }
    // Default settings contains in aphirri.application plugin
}

dependencies {
    implementation(libs.kotlin.serialization)

    implementation(projects.core.uikit)
    implementation(projects.core.navigation)
    implementation(projects.features.bottombar.bottombarUi)
    implementation(projects.features.home.homeUi)
    implementation(projects.features.meetings.meetingsUi)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}