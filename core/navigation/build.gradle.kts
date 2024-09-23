plugins {
    alias(libs.plugins.aphirri.library)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.core.uikit)

    implementation(libs.kotlin.serialization)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
}