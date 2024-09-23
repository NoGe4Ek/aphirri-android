package dev.aphirri.android

import dev.aphirri.android.home_ui.navigation.Home
import dev.aphirri.android.meetings_ui.navigation.Meetings
import dev.aphirri.android.navigation.models.Screen
import dev.aphirri.android.navigation.models.Services
import dev.aphirri.android.navigation.models.Storage

/**
 * Workaround.
 * All screens in project, cause toRoute<>() not cast to real instance
 * https://stackoverflow.com/questions/78489838/unable-to-get-route-object-from-currentbackstackentry-in-compose-navigation-outs
 */
val screens: List<Screen> = listOf(
    Home,
    Meetings,
    Services,
    Storage
)