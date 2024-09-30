package dev.aphirri.android.core.uikit.bars.toolbar.models

import androidx.compose.runtime.Stable

@Stable
sealed interface ContentType {
    data object Default : ContentType
    data object Overlay : ContentType
}