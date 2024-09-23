package dev.aphirri.android.meetings_ui.navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import dev.aphirri.android.meetings_ui.ui.MeetingsScreen
import dev.aphirri.android.navigation.models.Screen

fun NavGraphBuilder.meetingsDestination() {
    composable<Meetings>
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
        // val screen = navBackStackEntry.toRoute<Screen>()
        MeetingsScreen()
    }
}