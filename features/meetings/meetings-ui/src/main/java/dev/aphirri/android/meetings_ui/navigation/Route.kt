package dev.aphirri.android.meetings_ui.navigation

import dev.aphirri.android.navigation.models.Screen
import dev.aphirri.android.uikit.bars.toolbar.models.ToolbarType
import kotlinx.serialization.Serializable

@Serializable
data object Meetings : Screen("Meetings", ToolbarType.Light)