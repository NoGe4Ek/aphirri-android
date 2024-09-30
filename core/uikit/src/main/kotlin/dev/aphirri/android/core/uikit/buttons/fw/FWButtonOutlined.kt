package dev.aphirri.android.core.uikit.buttons.fw

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aphirri.android.core.uikit.extensions.clickableNoRipple
import dev.aphirri.android.core.uikit.theme.AphirriTheme

@Preview
@Composable
fun FWButtonOutlinedPreview() {
    AphirriTheme {
        FWButtonOutlined("Login in")
    }
}

@Composable
fun FWButtonOutlined(
    text: String,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .border(0.5.dp, AphirriTheme.colors.primary, RoundedCornerShape(8.dp))
            .clickableNoRipple { onClick() },
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier.padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = AphirriTheme.typography.button1,
                color = AphirriTheme.colors.primary
            )
        }
    }
}