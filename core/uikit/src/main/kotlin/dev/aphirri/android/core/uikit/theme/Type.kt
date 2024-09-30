package dev.aphirri.android.core.uikit.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import dev.aphirri.android.core.uikit.R

val somicFamily = FontFamily(
    Font(R.font.nt_somic_regular, FontWeight.Normal),
    Font(R.font.nt_somic_medium, FontWeight.Medium),
    Font(R.font.nt_somic_bold, FontWeight.Bold),
)

@Immutable
data class AphirriTypographyTokens(
    val headline1: TextStyle,
    val headline2: TextStyle,
    val headline3: TextStyle,
    val headline4: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val caption1: TextStyle,
    val caption1Bold: TextStyle,
    val caption2: TextStyle,
    val button1: TextStyle,
    val button2: TextStyle,
    val slogan1: TextStyle,
    val slogan2: TextStyle,
)