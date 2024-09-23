package dev.aphirri.android.bottombar.ui

import androidx.compose.animation.core.animate
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import dev.aphirri.android.features.bottombar.R
import dev.aphirri.android.core.uikit.R as UiKitR
import dev.aphirri.android.home_ui.navigation.Home
import dev.aphirri.android.meetings_ui.navigation.Meetings
import dev.aphirri.android.navigation.models.Screen
import dev.aphirri.android.navigation.models.Services
import dev.aphirri.android.navigation.models.Storage
import dev.aphirri.android.uikit.bars.bottombar.AphirriBottombar
import dev.aphirri.android.uikit.bars.bottombar.models.BottombarItem

internal fun NavController.bottombarNavigate(): (Screen) -> Unit = { screen ->
    this.navigate(screen) {
        launchSingleTop = true
        restoreState = true
        popUpTo(this@bottombarNavigate.graph.startDestinationId) {
            saveState = true
        }
        anim {
            enter = 0
            exit = 0
            popEnter = 0
            popExit = 0
        }
    }
}

@Composable
fun AphirriBottombar(navController: NavController) {
    AphirriBottombar(
        items = listOf(
            BottombarItem(
                title = "Home",
                icon = UiKitR.drawable.ic_home,
                screen = Home,
                onClick = navController.bottombarNavigate()
            ),
            BottombarItem(
                title = "Meetings",
                icon = UiKitR.drawable.ic_bell,
                screen = Meetings,
                onClick = navController.bottombarNavigate()
            ),
            BottombarItem(
                title = "Services",
                icon = UiKitR.drawable.ic_services,
                screen = Services,
                onClick = navController.bottombarNavigate()
            ),
            BottombarItem(
                title = "Storage",
                icon = UiKitR.drawable.ic_box,
                screen = Storage,
                onClick = navController.bottombarNavigate()
            ),
        )
    )
}