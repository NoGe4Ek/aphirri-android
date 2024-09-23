package dev.aphirri.android.uikit.bars.toolbar.content

import androidx.compose.runtime.Stable
import dev.aphirri.android.uikit.bars.toolbar.models.ContentType
import dev.aphirri.android.uikit.bars.toolbar.models.ToolbarItem
import dev.aphirri.android.uikit.bars.toolbar.models.ToolbarType

@Stable
data class AphirriToolbarContent(
    val slogan: ToolbarItem = ToolbarItem(),
    val title: ToolbarItem = ToolbarItem(),
    val caption: ToolbarItem = ToolbarItem(),
    val additional: ToolbarItem = ToolbarItem(),
    val toolbarType: ToolbarType = ToolbarType.Light,
    val contentType: ContentType = ContentType.Default,
)