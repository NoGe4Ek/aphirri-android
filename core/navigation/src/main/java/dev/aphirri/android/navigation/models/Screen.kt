package dev.aphirri.android.navigation.models

import android.os.Bundle
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavBackStackEntry
import dev.aphirri.android.uikit.bars.toolbar.models.ToolbarType
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
open class Screen(
    open val route: String,
    open val toolbarType: ToolbarType = ToolbarType.Light
)

/**
 * Workaround.
 * All screens in project, cause toRoute<>() not cast to real instance
 * https://stackoverflow.com/questions/78489838/unable-to-get-route-object-from-currentbackstackentry-in-compose-navigation-outs
 */
fun NavBackStackEntry?.toRoute(list: List<Screen>) =
    list.firstOrNull { it.route == this?.destination?.route?.split(".")?.last() }

@Serializable
data object Services : Screen("Services", ToolbarType.Light)

@Serializable
data object Storage : Screen("Storage", ToolbarType.Light)