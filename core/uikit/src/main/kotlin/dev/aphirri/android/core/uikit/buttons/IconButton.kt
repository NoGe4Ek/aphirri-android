package dev.aphirri.android.core.uikit.buttons

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aphirri.android.core.uikit.R
import dev.aphirri.android.core.uikit.extensions.clickableNoRipple
import dev.aphirri.android.core.uikit.theme.AphirriTheme

@Preview
@Composable
fun IconButtonPreview() {
    AphirriTheme {
        IconButton(
            R.drawable.ic_vk
        )
    }
}

@Composable
fun IconButton(
    @DrawableRes icon: Int,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clickableNoRipple(onClick)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = AphirriTheme.colors.primary
        )
    }
}