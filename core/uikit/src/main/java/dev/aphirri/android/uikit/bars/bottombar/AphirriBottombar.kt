package dev.aphirri.android.uikit.bars.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import dev.aphirri.android.core.uikit.R
import dev.aphirri.android.uikit.bars.bottombar.models.BottombarItem
import dev.aphirri.android.uikit.extensions.clickableNoRipple
import dev.aphirri.android.uikit.theme.AphirriTheme

val BOTTOM_APP_BAR_HEIGHT = 72.dp
val BOTTOM_APP_BAR_HORIZONTAL_PADDING = 24.dp
val START_BUTTON_PADDING = 16.dp
val ICON_TEXT_BUTTON_PADDING = 8.dp
val END_BUTTON_PADDING = 24.dp
val VERTICAL_BUTTON_PADDING = 8.dp

@Preview
@Composable
private fun AphirriBottomBarPreview(
    items: List<BottombarItem<String>> =
        listOf(
            BottombarItem(
                title = "Home",
                icon = R.drawable.ic_home,
                screen = "",
            ),
            BottombarItem(
                title = "Meetings",
                icon = R.drawable.ic_bell,
                screen = "",
            ),
            BottombarItem(
                title = "Services",
                icon = R.drawable.ic_services,
                screen = "",
            ),
            BottombarItem(
                title = "Storage",
                icon = R.drawable.ic_box,
                screen = "",
            ),
        )
) {
    AphirriTheme {
        AphirriBottombar(items)
    }
}

@Composable
fun <S> AphirriBottombar(
    items: List<BottombarItem<S>>
) {
    var currentTab by remember { mutableIntStateOf(0) }
    var previousTab by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier
            .height(BOTTOM_APP_BAR_HEIGHT)
            .shadow(
                elevation = AphirriTheme.elevation.bottomBar.elevation,
                shape = RectangleShape,
                ambientColor = AphirriTheme.elevation.bottomBar.ambientColor,
                spotColor = AphirriTheme.elevation.bottomBar.spotColor,
            ),
        color = AphirriTheme.colors.background,
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = BOTTOM_APP_BAR_HORIZONTAL_PADDING)
                .windowInsetsPadding(windowInsets),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items.fastForEachIndexed { i, item ->
                AphirriBottomAppBarButton(
                    isPrevious = currentTab < previousTab,
                    isSelected = currentTab == i,
                    item = item
                ) {
                    previousTab = currentTab
                    currentTab = i

                    item.onClick(item.screen)
                }
            }
        }
    }
}

@Composable
private fun <S> AphirriBottomAppBarButton(
    isPrevious: Boolean = false,
    isSelected: Boolean = false,
    item: BottombarItem<S>,
    onClick: () -> Unit = {}
) {
    val textMovementAnimation by isSelected.createTextMovementTransition(isPrevious)
    val backgroundAppearAnimation by isSelected.createBackgroundColorTransition()
    val changeIconColorAnimation by isSelected.createIconColorTransition()

    Row(
        modifier = Modifier
            .fillMaxHeight()
            .clickableNoRipple { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedPadding(isSelected) {
            Row(
                modifier = Modifier
                    .wrapContentWidth(unbounded = true)
                    .background(
                        color = backgroundAppearAnimation,
                        shape = AphirriTheme.shapes.medium
                    )
                    .padding(
                        start = if (isSelected) 0.dp else END_BUTTON_PADDING,
                        end = if (isSelected) 0.dp else END_BUTTON_PADDING,
                        top = VERTICAL_BUTTON_PADDING,
                        bottom = VERTICAL_BUTTON_PADDING
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AnimatedButtonExpand(isSelected) {
                    Spacer(modifier = Modifier.width(START_BUTTON_PADDING))
                }
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = "Tab icon",
                    tint = changeIconColorAnimation,
                )
                AnimatedButtonExpand(isSelected) {
                    Row {
                        Spacer(modifier = Modifier.width(ICON_TEXT_BUTTON_PADDING))
                        Text(
                            modifier = Modifier
                                .padding(end = END_BUTTON_PADDING)
                                .offset { IntOffset(x = textMovementAnimation.roundToPx(), y = 0) },
                            text = item.title,
                            color = AphirriTheme.colors.onPrimary,
                            style = AphirriTheme.typography.caption1
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Boolean.createTextMovementTransition(
    isPrevious: Boolean,
): State<Dp> {
    val lowBouncyMediumLowStiffness: FiniteAnimationSpec<Dp> = spring(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessMediumLow
    )
    val transition = updateTransition(targetState = this, label = "Text movement")
    return transition.animateDp(
        transitionSpec = { lowBouncyMediumLowStiffness },
        label = "Text movement"
    ) { selected ->
        if (selected) 0.dp else if (isPrevious) (-64).dp else 64.dp
    }
}

@Composable
private fun Boolean.createBackgroundColorTransition(): State<Color> {
    val noBouncyMediumLowStiffness: AnimationSpec<Color> = spring(
        dampingRatio = Spring.DampingRatioNoBouncy,
        stiffness = Spring.StiffnessMediumLow
    )
    return animateColorAsState(
        targetValue = if (this) AphirriTheme.colors.primary else Color.Transparent,
        animationSpec = if (this) noBouncyMediumLowStiffness else tween(durationMillis = 0),
        label = "Change background color"
    )
}

@Composable
private fun Boolean.createIconColorTransition(): State<Color> {
    return animateColorAsState(
        targetValue = if (this) AphirriTheme.colors.onPrimary else AphirriTheme.colors.primary,
        animationSpec = if (this) tween(durationMillis = 150) else tween(durationMillis = 0),
        label = "Change icon color"
    )
}

@Composable
private fun AnimatedPadding(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = expandHorizontally(animationSpec = tween(durationMillis = 300)),
        exit = shrinkHorizontally(animationSpec = tween(durationMillis = 300))
    ) {
        Spacer(modifier = Modifier.width(8.dp))
    }

    content()

    AnimatedVisibility(
        visible = visible,
        enter = expandHorizontally(animationSpec = tween(durationMillis = 300)),
        exit = shrinkHorizontally(animationSpec = tween(durationMillis = 300))
    ) {
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
private fun RowScope.AnimatedButtonExpand(
    visible: Boolean,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandHorizontally(
            animationSpec = tween(
                durationMillis = 300
            )
        ),
        exit = shrinkHorizontally(
            animationSpec = tween(
                durationMillis = 300
            )
        ),
        content = content
    )
}
