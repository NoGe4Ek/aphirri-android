package dev.aphirri.android.uikit.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

internal val MidnightBlue = Color(0xFF151F28)
internal val MidnightBlue50 = Color(0x80151F28)

internal val LightMidnightBlue = Color(0xFF677588)


internal val LightGray = Color(0xFFA9ACB0)
internal val Graphite = Color(0xFF43484D)

internal val White = Color(0xFFFFFFFF)
internal val GhostWhite = Color(0xFFF8F8F9)

internal val CrystalWater = Color(0xFF02EADD)

internal val HotPink = Color(0xFFC6006B)
internal val LimeGreen = Color(0xFF00C850)

@Immutable
data class AphirriColorTokens(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val tertiary: Color,
    val text: Color,
    val background: Color,
    val surface: Color,
    val infoError: Color,
    val infoCommon: Color,
    val infoSuccess: Color,
)