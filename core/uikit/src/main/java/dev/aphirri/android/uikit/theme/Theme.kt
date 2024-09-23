package dev.aphirri.android.uikit.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// todo TextSelectionColors, shapes, ripple, dark theme

val LocalAphirriShapeTokens = staticCompositionLocalOf {
    AphirriShapeTokens(
        none = DefaultShape,
        extraSmall = DefaultShape,
        small = DefaultShape,
        medium = DefaultShape,
        large = DefaultShape,
        extraLarge = DefaultShape,
    )
}

val LocalAphirriColorTokens = staticCompositionLocalOf {
    AphirriColorTokens(
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        secondary = Color.Unspecified,
        onSecondary = Color.Unspecified,
        tertiary = Color.Unspecified,
        text = Color.Unspecified,
        background = Color.Unspecified,
        surface = Color.Unspecified,
        infoError = Color.Unspecified,
        infoCommon = Color.Unspecified,
        infoSuccess = Color.Unspecified,
    )
}
val LocalAphirriTypographyTokens = staticCompositionLocalOf {
    AphirriTypographyTokens(
        headline1 = TextStyle.Default,
        headline2 = TextStyle.Default,
        headline3 = TextStyle.Default,
        headline4 = TextStyle.Default,
        body1 = TextStyle.Default,
        body2 = TextStyle.Default,
        caption1 = TextStyle.Default,
        caption1Bold = TextStyle.Default,
        caption2 = TextStyle.Default,
        button1 = TextStyle.Default,
        button2 = TextStyle.Default,
        slogan1 = TextStyle.Default,
        slogan2 = TextStyle.Default,
    )
}
val LocalAphirriElevationTokens = staticCompositionLocalOf {
    AphirriElevationTokens(
        toolbar = ElevationParams.Default,
        bottomBar = ElevationParams.Default,
    )
}

@Composable
fun AphirriTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = AphirriColorTokens(
        primary = MidnightBlue,
        onPrimary = GhostWhite,
        secondary = CrystalWater,
        onSecondary = MidnightBlue,
        tertiary = LightGray,
        text = Graphite,
        background = GhostWhite,
        surface = White,
        infoError = HotPink,
        infoCommon = LightMidnightBlue,
        infoSuccess = LimeGreen,
    )
    val typography = AphirriTypographyTokens(
        headline1 = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            lineHeight = 48.sp,
            letterSpacing = 5.76.sp // 16%
        ),
        headline2 = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.96.sp // 4%
        ),
        headline3 = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.8.sp // 4%
        ),
        headline4 = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.4.sp // 2%
        ),
        body1 = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.32.sp // 2%
        ),
        body2 = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.28.sp // 2%
        ),
        caption1 = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.24.sp // 2%
        ),
        caption1Bold = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.24.sp // 2%
        ),
        caption2 = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 12.sp,
            letterSpacing = 0.sp // 0%
        ),
        button1 = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.sp // 0%
        ),
        button2 = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
            lineHeight = 12.sp,
            letterSpacing = 0.64.sp // 4%
        ),
        slogan1 = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.96.sp // 8%
        ),
        slogan2 = TextStyle(
            fontFamily = somicFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            letterSpacing = 1.28.sp // 8%
        ),
    )

    val elevation = AphirriElevationTokens(
        toolbar = ElevationParams(
            elevation = 2.dp,
            ambientColor = MidnightBlue50,
            spotColor = MidnightBlue50
        ),
        bottomBar = ElevationParams(
            elevation = 8.dp,
            ambientColor = MidnightBlue50,
            spotColor = MidnightBlue50
        )
    )

    val shapes = AphirriShapeTokens(
        none = RectangleShape,
        extraSmall = RoundedCornerShape(4.dp),
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(12.dp),
        large = RoundedCornerShape(16.dp),
        extraLarge = RoundedCornerShape(24.dp),
    )

    CompositionLocalProvider(
        LocalAphirriColorTokens provides colors,
        LocalAphirriTypographyTokens provides typography,
        LocalAphirriElevationTokens provides elevation,
        LocalAphirriShapeTokens provides shapes,
        content = content
    )
}

// Use with eg. CustomTheme.elevation.default
object AphirriTheme {
    val colors: AphirriColorTokens
        @Composable
        get() = LocalAphirriColorTokens.current
    val typography: AphirriTypographyTokens
        @Composable
        get() = LocalAphirriTypographyTokens.current
    val elevation: AphirriElevationTokens
        @Composable
        get() = LocalAphirriElevationTokens.current
    val shapes: AphirriShapeTokens
        @Composable
        get() = LocalAphirriShapeTokens.current
}
