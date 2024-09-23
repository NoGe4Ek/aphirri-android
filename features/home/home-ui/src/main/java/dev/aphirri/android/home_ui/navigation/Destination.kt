package dev.aphirri.android.home_ui.navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.aphirri.android.home_ui.ui.HomeScreen
import dev.aphirri.android.navigation.models.Screen

fun NavGraphBuilder.homeDestination() {
    composable<Home>
//    (
//        enterTransition = {
//            slideInHorizontally(
//                spring(
//                    stiffness = Spring.StiffnessMedium,
//                    visibilityThreshold = IntOffset.VisibilityThreshold
//                )
//            )
//        },
//        exitTransition = null
//    )
    { navBackStackEntry ->
        //val screen = navBackStackEntry.toRoute<Home>()
        HomeScreen()
    }
}