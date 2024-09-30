package dev.aphirri.android.features.auth.auth_ui.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aphirri.android.features.auth.auth_ui.model.SocialLogin
import dev.aphirri.android.core.uikit.buttons.IconButton
import dev.aphirri.android.core.uikit.theme.AphirriTheme

@Preview
@Composable
fun SocialLoginRowPreview() {
    AphirriTheme {
        SocialLoginRow(
            items = listOf(SocialLogin.VK(), SocialLogin.Gmail())
        )
    }
}

@Composable
fun SocialLoginRow(
    items: List<SocialLogin>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login in with",
            style = AphirriTheme.typography.caption2,
            color = AphirriTheme.colors.text
        )
        Spacer(modifier = Modifier.height(6.dp))
        HorizontalDivider(
            modifier = Modifier.width(24.dp),
            thickness = 0.5.dp,
            color = AphirriTheme.colors.text
        )
        Row {
            items.listIterator()
            items.forEachIndexed { i, item ->
                IconButton(
                    icon = item.icon,
                    onClick = item.onClick
                )
                if (items.lastIndex != i)
                    Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}