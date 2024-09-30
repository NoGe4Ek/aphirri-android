package dev.aphirri.android.features.meetings.meetings_ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.aphirri.android.core.uikit.bars.toolbar.AphirriToolbar
import dev.aphirri.android.core.uikit.bars.toolbar.content.lightToolbar
import dev.aphirri.android.core.uikit.theme.AphirriTheme

@Composable
fun MeetingsScreen() {
    AphirriToolbar(
        aphirriToolbarContent = lightToolbar(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(AphirriTheme.colors.background)
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