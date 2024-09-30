package dev.aphirri.android.core.uikit.buttons.spannableText

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import dev.aphirri.android.core.uikit.theme.AphirriTheme

@Preview
@Composable
internal fun SpannableTextPreview() {
    AphirriTheme {
        SpannableText(
            "Hello, friend of my friend.",
            "friend"
        ) { Log.d("NOGE", "Test") }
    }
}

@Composable
fun SpannableText(
    text: String,
    spannableSubText: String,
    modifier: Modifier = Modifier,
    textColor: Color = AphirriTheme.colors.primary,
    onClick: () -> Unit = {},
) {
    val annotatedString = buildAnnotatedString {
        val startIndex = text.indexOf(spannableSubText)
        val endIndex = startIndex + spannableSubText.length
        withStyle(style = SpanStyle(color = textColor)) {
            append(text, 0, startIndex)
        }

        val link = LinkAnnotation.Clickable(
            spannableSubText,
            TextLinkStyles(
                SpanStyle(
                    color = AphirriTheme.colors.secondary,
                    textDecoration = TextDecoration.Underline
                )
            )
        ) {
            onClick()
        }
        withLink(link) { append(spannableSubText) }
        withStyle(style = SpanStyle(color = textColor)) {
            append(text, endIndex, text.length)
        }
    }

    Text(
        modifier = modifier,
        text = annotatedString
    )
}
