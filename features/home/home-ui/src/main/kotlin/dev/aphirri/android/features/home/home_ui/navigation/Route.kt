package dev.aphirri.android.features.home.home_ui.navigation

import dev.aphirri.android.core.navigation.models.Route
import dev.aphirri.android.core.uikit.bars.toolbar.models.ToolbarType
import kotlinx.serialization.Serializable

@Serializable
data object Home : Route("Home", ToolbarType.Dark())
