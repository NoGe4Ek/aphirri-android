package dev.aphirri.android.home_ui.navigation

import dev.aphirri.android.navigation.models.Route
import dev.aphirri.android.uikit.bars.toolbar.models.ToolbarType
import kotlinx.serialization.Serializable

@Serializable
data object Home : Route("Home", ToolbarType.Dark())
