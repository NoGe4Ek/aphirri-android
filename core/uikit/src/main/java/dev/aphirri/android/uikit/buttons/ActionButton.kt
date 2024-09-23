package dev.aphirri.android.uikit.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aphirri.android.core.uikit.R
import dev.aphirri.android.uikit.extensions.clickableNoRipple
import dev.aphirri.android.uikit.theme.AphirriTheme

@Preview
@Composable
fun ActionButtonPreview() {
    AphirriTheme {
        ActionButton()
    }
}

@Composable
fun ActionButton(
    @DrawableRes icon: Int = R.drawable.ic_action_settings,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickableNoRipple(onClick = onClick),
        color = AphirriTheme.colors.onPrimary
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(icon),
                contentDescription = null
            )
        }
    }
}