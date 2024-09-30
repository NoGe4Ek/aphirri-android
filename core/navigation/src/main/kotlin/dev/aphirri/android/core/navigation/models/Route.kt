package dev.aphirri.android.core.navigation.models

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import dev.aphirri.android.core.uikit.bars.toolbar.models.ToolbarType
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
open class Route(
    open val route: String,
    @Contextual
    open val toolbarType: ToolbarType
)

@Serializable
sealed interface NavigationRoute {
    @Serializable
    data object HomeRoot : NavigationRoute

    @Serializable
    data object MeetingsRoot : NavigationRoute

    @Serializable
    data object ServicesRoot : NavigationRoute

    @Serializable
    data object StorageRoot : NavigationRoute
}

@Serializable
data object Services : Route("Services", ToolbarType.Light)

@Serializable
data object Storage : Route("Storage", ToolbarType.Light)

/**
 * Workaround.
 * All screens in project, cause toRoute<>() not cast to real instance
 * https://stackoverflow.com/questions/78489838/unable-to-get-route-object-from-currentbackstackentry-in-compose-navigation-outs
 */
fun NavBackStackEntry?.toRoute(list: List<Route>) =
    list.firstOrNull { it.route == this?.destination?.route?.split(".")?.last() }

// For map NavTypes in NavHost
inline fun <reified T> navTypeOf(
    isNullableAllowed: Boolean = false,
    json: Json = Json,
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let(json::decodeFromString)

    override fun parseValue(value: String): T = json.decodeFromString(Uri.decode(value))

    override fun serializeAsValue(value: T): String = Uri.encode(json.encodeToString(value))

    override fun put(bundle: Bundle, key: String, value: T) =
        bundle.putString(key, json.encodeToString(value))

}
