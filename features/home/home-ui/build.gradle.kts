plugins {
    alias(libs.plugins.aphirri.feature)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.core.navigation)

    implementation(libs.kotlin.serialization)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.androidx.navigation.compose)

    implementation(libs.material)
    implementation(libs.androidx.material3)
}