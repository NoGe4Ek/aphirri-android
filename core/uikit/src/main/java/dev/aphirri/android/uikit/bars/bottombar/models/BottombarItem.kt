package dev.aphirri.android.uikit.bars.bottombar.models

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable

@Stable
data class BottombarItem<S>(
    val title: String,
    @DrawableRes val icon: Int,
    val screen: S,
    val onClick: (S) -> Unit = {}
)