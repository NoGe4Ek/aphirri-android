package dev.aphirri.android.features.home.home_ui.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import dev.aphirri.android.core.uikit.bars.toolbar.AphirriToolbar
import dev.aphirri.android.core.uikit.bars.toolbar.content.darkToolbar

@Composable
fun HomeScreen() {
    AphirriToolbar(
        aphirriToolbarContent = darkToolbar(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp)
            ) {
                items(100) {
                    Text("Test $it")
                }
            }
        }
    }
}