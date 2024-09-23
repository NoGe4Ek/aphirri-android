package dev.aphirri.android.uikit.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Immutable
data class AphirriElevationTokens(
    val toolbar: ElevationParams,
    val bottomBar: ElevationParams,
)

@Immutable
data class ElevationParams(
    val elevation: Dp,
    val ambientColor: Color,
    val spotColor: Color,
) {
    companion object {
        val Default = ElevationParams(Dp.Unspecified, Color.Unspecified, Color.Unspecified)
    }
}
