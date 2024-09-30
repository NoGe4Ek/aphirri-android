package dev.aphirri.android.core.uikit.editText

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.aphirri.android.core.uikit.R
import dev.aphirri.android.core.uikit.theme.AphirriTheme
import dev.aphirri.android.core.uikit.theme.CrystalWater20
import dev.aphirri.android.core.uikit.theme.Graphite
import dev.aphirri.android.core.uikit.theme.LightGray
import dev.aphirri.android.core.uikit.theme.MidnightBlue
import kotlinx.coroutines.delay

@Preview
@Composable
fun AphirriEditTextPreview() {
    AphirriTheme {
        val (focusRequester) = FocusRequester.createRefs()
        val keyboardController = LocalSoftwareKeyboardController.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AphirriTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AphirriEditText(
                modifier = Modifier.focusRequester(focusRequester),
                label = "First name",
                hint = "Type here...",
                text = "",
                icon = R.drawable.ic_profile,
                onTextChange = { },
                keyboardActions = KeyboardActions(onNext = { focusRequester.requestFocus() }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(16.dp))

            AphirriEditText(
                modifier = Modifier.focusRequester(focusRequester),
                label = "First name",
                text = "",
                icon = R.drawable.ic_profile,
                onTextChange = { },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            AphirriEditText(
                modifier = Modifier.focusRequester(focusRequester),
                label = "First name",
                text = "",
                icon = R.drawable.ic_profile,
                onTextChange = { },
                isDisable = true
            )
        }
    }
}

enum class EditTextState(val color: Color) {
    DEFAULT(Graphite),
    FOCUSED(MidnightBlue),
    DISABLED(LightGray)
}

@Composable
fun AphirriEditText(
    modifier: Modifier = Modifier,
    label: String = "",
    hint: String = "Type...",
    text: String = "",
    @DrawableRes icon: Int,
    isDisable: Boolean = false,
    isRequired: Boolean = false,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    onTextChange: (String) -> Unit = {}
) {
    var textState by remember { mutableStateOf(text) }

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState().value
    val editFieldState: EditTextState = remember(isFocused, isDisable) {
        when {
            isDisable -> EditTextState.DISABLED
            isFocused -> EditTextState.FOCUSED
            else -> EditTextState.DEFAULT
        }
    }
    var showCursor by remember { mutableStateOf(true) }
    var isCursorRecentlyChanged by remember { mutableStateOf(false) }

    val customTextSelectionColors = TextSelectionColors(
        handleColor = AphirriTheme.colors.primary,
        backgroundColor = CrystalWater20
    )

    LaunchedEffect(textState) {
        while (true) {
            if (isCursorRecentlyChanged) {
                showCursor = true
                isCursorRecentlyChanged = false
            } else {
                showCursor = !showCursor
            }
            delay(500)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                color = if (editFieldState == EditTextState.FOCUSED) editFieldState.color else AphirriTheme.colors.tertiary,
                style = AphirriTheme.typography.caption1
            )
            if (isRequired) {
                Spacer(modifier = Modifier.width(8.dp))
                Surface(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(
                            CircleShape
                        ),
                    color = AphirriTheme.colors.primary
                ) {}
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
            BasicTextField(
                modifier = modifier.fillMaxWidth(),
                value = textState,
                onValueChange = {
                    textState = it
                    isCursorRecentlyChanged = true
                    onTextChange(it)
                },
                singleLine = true,
                textStyle = AphirriTheme.typography.body1.copy(color = editFieldState.color),
                enabled = editFieldState != EditTextState.DISABLED,
                cursorBrush = SolidColor(AphirriTheme.colors.primary),
                keyboardOptions = if (isPassword) keyboardOptions.copy(keyboardType = KeyboardType.Password) else keyboardOptions,
                keyboardActions = keyboardActions,
                // obfuscate and remove option to copy text in password field
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                decorationBox = { innerTextField ->
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier.size(12.dp, 14.dp),
                                painter = painterResource(id = icon),
                                contentDescription = null,
                                tint = editFieldState.color
                            )
                            Spacer(modifier = Modifier.width(24.dp))
                            if (textState.isEmpty() && editFieldState != EditTextState.FOCUSED)
                                Text(
                                    text = hint,
                                    style = AphirriTheme.typography.body1,
                                    color = AphirriTheme.colors.tertiary
                                )
                            if (isPassword)
                                Row(
                                    modifier = Modifier.height(24.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    repeat(textState.length) {
                                        Surface(
                                            modifier = Modifier.size(4.dp),
                                            shape = CircleShape,
                                            color = AphirriTheme.colors.text
                                        ) {}
                                        Spacer(modifier = Modifier.width(10.dp))
                                    }
                                    if (showCursor && editFieldState == EditTextState.FOCUSED) {
                                        VerticalDivider(
                                            modifier = Modifier.height(20.dp),
                                            color = AphirriTheme.colors.primary,
                                            thickness = 1.dp
                                        )
                                    }
                                }
                            else
                                Box(
                                    modifier = Modifier.height(24.dp)
                                ) {
                                    innerTextField()
                                }
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                        Box(
                            modifier = Modifier.height(2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            HorizontalDivider(
                                thickness = if (editFieldState == EditTextState.FOCUSED) 1.dp else 0.5.dp,
                                color = editFieldState.color
                            )
                        }
                    }
                },
                interactionSource = interactionSource
            )
        }
    }
}
