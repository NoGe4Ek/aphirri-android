package dev.aphirri.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.aphirri.android.bottombar.ui.AphirriBottombar
import dev.aphirri.android.home_ui.navigation.Home
import dev.aphirri.android.home_ui.navigation.homeDestination
import dev.aphirri.android.meetings_ui.navigation.meetingsDestination
import dev.aphirri.android.navigation.models.toRoute
import dev.aphirri.android.uikit.bars.toolbar.AphirriToolbar
import dev.aphirri.android.uikit.bars.toolbar.content.darkToolbar
import dev.aphirri.android.uikit.bars.toolbar.models.ToolbarType
import dev.aphirri.android.uikit.theme.AphirriTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AphirriTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = AphirriTheme.colors.background,
                    bottomBar = {
                        AphirriBottombar(navController)
                    },
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .background(
                                if (navBackStackEntry.toRoute(screens)?.toolbarType is ToolbarType.Dark)
                                    AphirriTheme.colors.primary
                                else
                                    AphirriTheme.colors.background
                            )
                    ) {
                        Spacer(modifier = Modifier.height(innerPadding.calculateTopPadding()))
                        NavHost(
                            navController = navController,
                            startDestination = Home,
                            enterTransition = {
                                // you can change whatever you want transition
                                EnterTransition.None
                            },
                            exitTransition = {
                                // you can change whatever you want transition
                                ExitTransition.None
                            }
                        ) {
                            homeDestination()
                            meetingsDestination()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AphirriTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = AphirriTheme.colors.background,
            bottomBar = { AphirriBottombar(rememberNavController()) },
        ) { innerPadding ->
            AphirriToolbar(
                aphirriToolbarContent = darkToolbar()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding)
                        .padding(start = 32.dp)
                ) {
                    items(100) {
                        Text("Test $it")
                    }
                }
            }
        }
    }
}
