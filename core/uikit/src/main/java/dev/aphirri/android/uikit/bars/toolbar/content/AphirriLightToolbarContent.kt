package dev.aphirri.android.uikit.bars.toolbar.content

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.aphirri.android.uikit.bars.toolbar.models.ContentType
import dev.aphirri.android.uikit.bars.toolbar.models.ToolbarItem
import dev.aphirri.android.uikit.bars.toolbar.models.ToolbarType
import dev.aphirri.android.uikit.theme.AphirriTheme

fun lightToolbar() = AphirriToolbarContent(
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
                    color = AphirriTheme.colors.tertiary
                )
                VerticalDivider(
                    modifier = Modifier.height(24.dp),
                    thickness = 0.5.dp,
                    color = AphirriTheme.colors.tertiary
                )
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = "Ready for it?",
                    style = AphirriTheme.typography.slogan2,
                    color = AphirriTheme.colors.primary
                )
            }
        },
        needCollapse = false
    ),
    title = ToolbarItem(
        item = {
            Text(
                modifier = Modifier.padding(top = 28.dp),
                text = "Meetings",
                style = AphirriTheme.typography.headline2,
                color = AphirriTheme.colors.primary
            )
        },
        needCollapse = false
    ),
    caption = ToolbarItem(
        item = {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = AnnotatedString.Builder().apply {
                    append("You can ")
                    withStyle(
                        style = SpanStyle(
                            color = AphirriTheme.colors.secondary,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append("create")
                    }
                    append(" and look through meetings here.")
                }.toAnnotatedString(),
                style = AphirriTheme.typography.caption1,
                color = AphirriTheme.colors.tertiary
            )
        },
        needCollapse = true
    ),
    additional = ToolbarItem(),
    toolbarType = ToolbarType.Light,
    contentType = ContentType.Default,
)