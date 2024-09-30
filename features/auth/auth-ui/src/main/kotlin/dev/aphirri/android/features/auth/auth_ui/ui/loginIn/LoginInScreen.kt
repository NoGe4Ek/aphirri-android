package dev.aphirri.android.features.auth.auth_ui.ui.loginIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aphirri.android.core.uikit.R
import dev.aphirri.android.core.uikit.buttons.fw.FWButtonFilled
import dev.aphirri.android.core.uikit.buttons.spannableText.SpannableText
import dev.aphirri.android.core.uikit.editText.AphirriEditText
import dev.aphirri.android.core.uikit.theme.AphirriTheme

@Preview
@Composable
private fun LoginInScreenPreview() {
    AphirriTheme {
        LoginInScreen()
    }
}

@Composable
fun LoginInScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AphirriTheme.colors.background)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(64.dp))
            Text(
                text = "Welcome back",
                style = AphirriTheme.typography.headline2,
                color = AphirriTheme.colors.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Please enter your details to login in.",
                style = AphirriTheme.typography.caption1,
                color = AphirriTheme.colors.tertiary
            )
            Spacer(modifier = Modifier.height(64.dp))
            AphirriEditText(
                label = "Email",
                icon = R.drawable.ic_email
            )
            Spacer(modifier = Modifier.height(24.dp))
            AphirriEditText(
                label = "Password",
                icon = R.drawable.ic_password,
                isPassword = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            SpannableText(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 24.dp),
                text = "Forgot password?",
                spannableSubText = "Forgot password?",
                onClick = {

                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                FWButtonFilled("Login in")
                Spacer(modifier = Modifier.height(12.dp))
                SpannableText(
                    text = "Don’t have an account? Sign up",
                    spannableSubText = "Sign up",
                    onClick = {

                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}