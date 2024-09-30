package dev.aphirri.android.features.auth.auth_ui.ui.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aphirri.android.features.auth.auth_ui.model.SocialLogin
import dev.aphirri.android.features.auth.auth_ui.ui.components.SocialLoginRow
import dev.aphirri.android.core.uikit.buttons.fw.FWButtonFilled
import dev.aphirri.android.core.uikit.buttons.fw.FWButtonOutlined
import dev.aphirri.android.core.uikit.theme.AphirriTheme

@Preview
@Composable
fun StartScreenPreview() {
    AphirriTheme {
        StartScreen()
    }
}

@Composable
fun StartScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AphirriTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "Aphirri",
            style = AphirriTheme.typography.headline1,
            color = AphirriTheme.colors.primary
        )
        Spacer(modifier = Modifier.height(285.dp))
        FWButtonFilled("Sign up")
        Spacer(modifier = Modifier.height(12.dp))
        FWButtonOutlined("Login in")
        Spacer(modifier = Modifier.height(24.dp))
        SocialLoginRow(
            items = listOf(
                SocialLogin.VK(),
                SocialLogin.Gmail()
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}