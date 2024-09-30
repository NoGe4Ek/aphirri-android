package dev.aphirri.android

import dev.aphirri.android.features.home.home_ui.navigation.Home
import dev.aphirri.android.features.meetings.meetings_ui.navigation.Meetings
import dev.aphirri.android.core.navigation.models.Route
import dev.aphirri.android.core.navigation.models.Services
import dev.aphirri.android.core.navigation.models.Storage

/**
 * Workaround.
 * All screens in project, cause toRoute<>() not cast to real instance
 * https://stackoverflow.com/questions/78489838/unable-to-get-route-object-from-currentbackstackentry-in-compose-navigation-outs
 */
val routes: List<Route> = listOf(
    Home,
    Meetings,
    Services,
    Storage
)