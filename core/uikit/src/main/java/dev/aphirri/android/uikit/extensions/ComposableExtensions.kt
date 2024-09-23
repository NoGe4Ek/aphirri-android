package dev.aphirri.android.uikit.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun Modifier.clickableNoRipple(
    onClick: () -> Unit
) = this.clickable(
    interactionSource = remember { MutableInteractionSource() },
    indication = null
) { onClick() }