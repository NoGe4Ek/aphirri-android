plugins {
    alias(libs.plugins.aphirri.feature)
}

dependencies {
    implementation(projects.features.home.homeUi)
    implementation(projects.features.meetings.meetingsUi)
    implementation(projects.core.navigation)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material3)
}