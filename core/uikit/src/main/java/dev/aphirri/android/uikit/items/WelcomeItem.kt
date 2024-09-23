package dev.aphirri.android.uikit.items

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dev.aphirri.android.core.uikit.R
import dev.aphirri.android.uikit.theme.AphirriTheme

data class ProfileUI(
    @DrawableRes val image: Int = R.drawable.profile1,
    val firstName: String = "Name",
    val lastName: String = "Surname"
)

@Preview
@Composable
fun WelcomeItemPreview() {
    AphirriTheme {
        WelcomeItem(ProfileUI())
    }
}

@Composable
fun WelcomeItem(
    profile: ProfileUI,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = profile.image),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            alignment = { _, _, _ ->
                IntOffset(x = 0, -16)
            },
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier.padding(start = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Welcome back,",
                style = AphirriTheme.typography.body1,
                color = AphirriTheme.colors.onPrimary
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "${profile.firstName} ${profile.lastName}",
                style = AphirriTheme.typography.headline2,
                color = AphirriTheme.colors.onPrimary
            )
        }
    }
}