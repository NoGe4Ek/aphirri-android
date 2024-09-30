package dev.aphirri.android.features.meetings.meetings_ui.navigation

import dev.aphirri.android.core.navigation.models.Route
import dev.aphirri.android.core.uikit.bars.toolbar.models.ToolbarType
import kotlinx.serialization.Serializable

@Serializable
data object Meetings : Route("Meetings", ToolbarType.Light)
