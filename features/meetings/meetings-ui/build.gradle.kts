plugins {
    alias(libs.plugins.aphirri.feature)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.core.navigation)

    implementation(libs.kotlin.serialization)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material3)
}