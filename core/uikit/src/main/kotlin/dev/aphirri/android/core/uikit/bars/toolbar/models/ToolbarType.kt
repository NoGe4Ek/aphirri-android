package dev.aphirri.android.core.uikit.bars.toolbar.models

import androidx.annotation.DrawableRes

sealed class ToolbarType {
    data class Dark(@DrawableRes val image: Int? = null) : ToolbarType()
    data object Light : ToolbarType()
}