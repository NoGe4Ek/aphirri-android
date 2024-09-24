package dev.aphirri.android.meetings_ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.aphirri.android.meetings_ui.ui.MeetingsScreen
import dev.aphirri.android.navigation.models.NavigationRoute
import dev.aphirri.android.navigation.models.Services
import dev.aphirri.android.navigation.models.Storage
import dev.aphirri.android.uikit.theme.AphirriTheme

fun NavGraphBuilder.meetingsDestination() {
    navigation<NavigationRoute.MeetingsRoot>(startDestination = Meetings) {
        composable<Meetings> { navBackStackEntry ->
            MeetingsScreen()
        }
    }
}

fun NavGraphBuilder.servicesDestination() {
    navigation<NavigationRoute.ServicesRoot>(startDestination = Services) {
        composable<Services> { navBackStackEntry ->
            // val screen = navBackStackEntry.toRoute<Screen>()
            Box(
                modifier = Modifier.fillMaxSize().background(AphirriTheme.colors.background)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp)
                ) {
                    items(100) {
                        Text("Test123123 $it")
                    }
                }
            }
        }
    }
}

fun NavGraphBuilder.storageDestination() {
    navigation<NavigationRoute.StorageRoot>(startDestination = Storage) {
        composable<Storage> { navBackStackEntry ->
            // val screen = navBackStackEntry.toRoute<Screen>()
            Box(
                modifier = Modifier.fillMaxSize().background(AphirriTheme.colors.background)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp)
                ) {
                    items(100) {
                        Text("Piz poz paz $it")
                    }
                }
            }
        }
    }
}