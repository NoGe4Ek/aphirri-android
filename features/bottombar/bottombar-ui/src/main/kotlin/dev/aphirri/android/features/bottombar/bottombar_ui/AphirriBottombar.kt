package dev.aphirri.android.features.bottombar.bottombar_ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import dev.aphirri.android.core.navigation.models.NavigationRoute
import dev.aphirri.android.core.uikit.bars.bottombar.AphirriBottombarScaffold
import dev.aphirri.android.core.uikit.bars.bottombar.models.BottombarItem
import dev.aphirri.android.core.uikit.R as UiKitR

// Here most of app use FLAG_ACTIVITY_REORDER_TO_FRONT behaviour
internal fun NavController.bottombarNavigate(
    currentRoute: NavigationRoute
): (NavigationRoute) -> Unit = { route ->
    val startDestinationId = this@bottombarNavigate.graph.findStartDestination().id

    if (currentRoute != route) {
        this.navigate(route) {
            popUpTo(startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun AphirriBottombar(
    navController: NavController
) {
    val currentBottombarRoute by navController.currentRouteAsState()

    AphirriBottombarScaffold(
        items = listOf(
            BottombarItem(
                title = "Home",
                icon = UiKitR.drawable.ic_home,
                route = NavigationRoute.HomeRoot
            ),
            BottombarItem(
                title = "Meetings",
                icon = UiKitR.drawable.ic_bell,
                route = NavigationRoute.MeetingsRoot
            ),
            BottombarItem(
                title = "Services",
                icon = UiKitR.drawable.ic_services,
                route = NavigationRoute.ServicesRoot
            ),
            BottombarItem(
                title = "Storage",
                icon = UiKitR.drawable.ic_box,
                route = NavigationRoute.StorageRoot
            ),
        ),
        selectedTab = currentBottombarRoute,
        onSelectTab = navController.bottombarNavigate(
            currentRoute = currentBottombarRoute
        )
    )
}

@Stable
@Composable
private fun NavController.currentRouteAsState(): State<NavigationRoute> {
    val selectedItem = remember { mutableStateOf<NavigationRoute>(NavigationRoute.HomeRoot) }
    DisposableEffect(key1 = this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any { it.hasRoute<NavigationRoute.HomeRoot>() } -> {
                    selectedItem.value = NavigationRoute.HomeRoot
                }

                destination.hierarchy.any { it.hasRoute<NavigationRoute.MeetingsRoot>() } -> {
                    selectedItem.value = NavigationRoute.MeetingsRoot
                }

                destination.hierarchy.any { it.hasRoute<NavigationRoute.ServicesRoot>() } -> {
                    selectedItem.value = NavigationRoute.ServicesRoot
                }

                destination.hierarchy.any { it.hasRoute<NavigationRoute.StorageRoot>() } -> {
                    selectedItem.value = NavigationRoute.StorageRoot
                }
            }

        }
        addOnDestinationChangedListener(listener)
        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }
    return selectedItem
}