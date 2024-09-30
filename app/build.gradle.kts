plugins {
    alias(libs.plugins.aphirri.application)
    alias(libs.plugins.kotlin.serialization)
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

    implementation(libs.androidx.core.splashscreen)
}
