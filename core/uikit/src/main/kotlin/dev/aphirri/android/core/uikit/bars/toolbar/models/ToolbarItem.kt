package dev.aphirri.android.core.uikit.bars.toolbar.models

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
data class ToolbarItem(
    val item: @Composable () -> Unit = {},
    val offsetY: Dp = 0.dp,
    val endScale: Float = 1f,
    val needCollapse: Boolean = false
)
