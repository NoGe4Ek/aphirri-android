package dev.aphirri.android.core.uikit.buttons.fw

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aphirri.android.core.uikit.extensions.clickableNoRipple
import dev.aphirri.android.core.uikit.theme.AphirriTheme

@Preview
@Composable
fun FWButtonFilledPreview() {
    AphirriTheme {
        FWButtonFilled("Sign up")
    }
}

@Composable
fun FWButtonFilled(
    text: String,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .clickableNoRipple { onClick() },
        color = AphirriTheme.colors.primary,
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier.padding(vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = AphirriTheme.typography.button1,
                color = AphirriTheme.colors.onPrimary
            )
        }
    }
}