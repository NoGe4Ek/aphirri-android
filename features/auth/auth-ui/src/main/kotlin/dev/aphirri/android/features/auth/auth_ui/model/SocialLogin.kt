package dev.aphirri.android.features.auth.auth_ui.model

import androidx.annotation.DrawableRes
import dev.aphirri.android.core.uikit.R as UiKitR

sealed class SocialLogin(@DrawableRes val icon: Int, open val onClick: () -> Unit) {
    data class VK(override val onClick: () -> Unit = {}): SocialLogin(UiKitR.drawable.ic_vk, onClick)
    data class Gmail(override val onClick: () -> Unit = {}): SocialLogin(UiKitR.drawable.ic_gmail, onClick)
}