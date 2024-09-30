package dev.aphirri.android.features.home.home_ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.aphirri.android.features.home.home_ui.ui.HomeScreen
import dev.aphirri.android.core.navigation.models.NavigationRoute

fun NavGraphBuilder.homeDestination() {
    navigation<NavigationRoute.HomeRoot>(startDestination = Home) {
        composable<Home> { navBackStackEntry ->
            HomeScreen()
        }
    }
}