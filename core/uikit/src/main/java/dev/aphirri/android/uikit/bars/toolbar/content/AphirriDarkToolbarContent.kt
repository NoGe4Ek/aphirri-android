package dev.aphirri.android.uikit.bars.toolbar.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.aphirri.android.core.uikit.R
import dev.aphirri.android.uikit.bars.toolbar.models.ContentType
import dev.aphirri.android.uikit.bars.toolbar.models.ToolbarItem
import dev.aphirri.android.uikit.bars.toolbar.models.ToolbarType
import dev.aphirri.android.uikit.buttons.ActionButton
import dev.aphirri.android.uikit.items.ProfileUI
import dev.aphirri.android.uikit.items.WelcomeItem
import dev.aphirri.android.uikit.theme.AphirriTheme

fun darkToolbar() = AphirriToolbarContent(
    slogan = ToolbarItem(
        item = {
            Row(
                modifier = Modifier.padding(top = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 4.dp),
                    text = "aphirri",
                    style = AphirriTheme.typography.slogan1,
                    color = AphirriTheme.colors.onPrimary
                )
                VerticalDivider(
                    modifier = Modifier.height(24.dp),
                    thickness = 0.5.dp,
                    color = AphirriTheme.colors.onPrimary
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Ready for it?",
                    style = AphirriTheme.typography.slogan2,
                    color = AphirriTheme.colors.secondary
                )
            }
        },
        needCollapse = false
    ),
    title = ToolbarItem(
        item = {
            Column {
                WelcomeItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, start = 16.dp),
                    profile = ProfileUI()
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
        },
        offsetY = 0.dp,
        needCollapse = false
    ),
    caption = ToolbarItem(
        item = {
            Row(
                modifier = Modifier.padding(top = 20.dp, bottom = 12.dp)
            ) {
                ActionButton(icon = R.drawable.ic_action_settings)
                Spacer(modifier = Modifier.width(24.dp))
                ActionButton(icon = R.drawable.ic_action_friends)
                Spacer(modifier = Modifier.width(24.dp))
                ActionButton(icon = R.drawable.ic_action_sign_out)
            }
        },
        needCollapse = true
    ),
    additional = ToolbarItem(),
    toolbarType = ToolbarType.Dark(),
    contentType = ContentType.Overlay,
)