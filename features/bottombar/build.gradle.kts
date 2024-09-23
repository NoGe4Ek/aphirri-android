plugins {
    alias(libs.plugins.aphirri.feature)
}

dependencies {
    implementation(projects.features.home.homeUi)
    implementation(projects.features.meetings.meetingsUi)
    implementation(projects.core.navigation)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
}