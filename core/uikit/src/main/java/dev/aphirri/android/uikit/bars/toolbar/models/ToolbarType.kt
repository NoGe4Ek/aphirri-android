package dev.aphirri.android.uikit.bars.toolbar.models

import androidx.annotation.DrawableRes

sealed interface ToolbarType {
    data class Dark(@DrawableRes val image: Int? = null) : ToolbarType
    data object Light : ToolbarType
}