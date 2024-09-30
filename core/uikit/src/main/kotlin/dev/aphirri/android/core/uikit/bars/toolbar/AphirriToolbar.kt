package dev.aphirri.android.core.uikit.bars.toolbar

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.DebugFlags
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.Transition
import dev.aphirri.android.core.uikit.bars.toolbar.content.AphirriToolbarContent
import dev.aphirri.android.core.uikit.bars.toolbar.content.darkToolbar
import dev.aphirri.android.core.uikit.bars.toolbar.models.ContentType
import dev.aphirri.android.core.uikit.bars.toolbar.models.ToolbarItem
import dev.aphirri.android.core.uikit.bars.toolbar.models.ToolbarType
import dev.aphirri.android.core.uikit.bars.toolbar.utils.ToolbarConstrainedRefs.Companion.refs
import dev.aphirri.android.core.uikit.bars.toolbar.utils.ToolbarLayoutId
import dev.aphirri.android.core.uikit.theme.AphirriTheme
import kotlin.math.roundToInt

@Preview
@Composable
private fun AphirriToolbarPreview() {
    AphirriTheme {
        AphirriToolbar(
            aphirriToolbarContent = darkToolbar()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                for (i in 0..99) {
                    Text("Test")
                }
            }
        }
    }
}

private enum class SwipingStates {
    EXPANDED,
    COLLAPSED
}

@OptIn(ExperimentalMotionApi::class, ExperimentalFoundationApi::class)
@Composable
fun AphirriToolbar(
    innerPadding: PaddingValues = PaddingValues(),
    aphirriToolbarContent: AphirriToolbarContent = AphirriToolbarContent(),
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val state: AnchoredDraggableState<SwipingStates> = remember {
        AnchoredDraggableState(
            initialValue = SwipingStates.EXPANDED,
            anchors = DraggableAnchors {
                SwipingStates.COLLAPSED at 84f
                SwipingStates.EXPANDED at 256f
            },
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 48.dp.toPx() } },
            snapAnimationSpec = spring(
                stiffness = Spring.StiffnessMediumLow
            ),
            decayAnimationSpec = decayAnimationSpec
        )
    }

    val connection = remember {
        object : NestedScrollConnection {

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                return if (delta < 0) {
                    val current = state.requireOffset()
                    val potentiallyConsumed = current + delta
                    val clamped = potentiallyConsumed.coerceIn(84f, 256f)
                    val deltaToConsume = clamped - current

                    state.dispatchRawDelta(deltaToConsume)
                    Offset(0f, deltaToConsume)
                } else {
                    Offset.Zero
                }
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                val delta = available.y
                val current = state.requireOffset()
                val potentiallyConsumed = current + delta
                val clamped = potentiallyConsumed.coerceIn(84f, 256f)
                val deltaToConsume = clamped - current

                state.dispatchRawDelta(deltaToConsume)
                return Offset(0f, deltaToConsume)
            }

            override suspend fun onPostFling(
                consumed: Velocity,
                available: Velocity
            ): Velocity {
                state.settle(available.y)
                return super.onPostFling(consumed, available)
            }
        }
    }
    Column(
        // Status bar color
        modifier = Modifier.background(
            if (aphirriToolbarContent.toolbarType is ToolbarType.Light)
                AphirriTheme.colors.primary
            else
                AphirriTheme.colors.background
        )
    ) {
        Spacer(modifier = Modifier.height(innerPadding.calculateTopPadding()))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .anchoredDraggable(
                    state = state,
                    orientation = Orientation.Vertical
                )
                .nestedScroll(connection)
                // Background for rounded corner animation
                .background(AphirriTheme.colors.background)
        ) {
            MotionLayout(
                start = startConstraintSet(),
                end = endConstraintSet(aphirriToolbarContent),
                transition = createTransition(),
                debugFlags = DebugFlags.None,
                progress = if (state.targetValue == SwipingStates.COLLAPSED) state.progress(
                    SwipingStates.EXPANDED,
                    SwipingStates.COLLAPSED
                ) else 1f - state.progress(SwipingStates.COLLAPSED, SwipingStates.EXPANDED),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = innerPadding.calculateBottomPadding()),
            ) {
                if (aphirriToolbarContent.contentType is ContentType.Overlay)
                    ContainerAnimation()
                Content(content, aphirriToolbarContent.contentType, ToolbarLayoutId.CONTENT)

                Shadow()
                Container(aphirriToolbarContent.toolbarType)
                ContainerImage(aphirriToolbarContent.toolbarType)

                WithId(aphirriToolbarContent.slogan, ToolbarLayoutId.SLOGAN)
                WithId(aphirriToolbarContent.title, ToolbarLayoutId.TITLE)
                WithId(aphirriToolbarContent.caption, ToolbarLayoutId.CAPTION)
                WithId(aphirriToolbarContent.additional, ToolbarLayoutId.ADDITIONAL)
            }
        }
    }

}

@Suppress("SameParameterValue")
@Composable
private fun Content(
    content: @Composable () -> Unit,
    contentType: ContentType,
    toolbarId: ToolbarLayoutId
) {
    if (contentType is ContentType.Overlay)
        Surface(
            modifier = Modifier
                .layoutId(toolbarId)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            color = AphirriTheme.colors.background
        ) {
            content()
        }
    else
        Box(
            modifier = Modifier
                .layoutId(toolbarId)
                .background(AphirriTheme.colors.background)
        ) {
            content()
        }
}

@Composable
private fun WithId(toolbarItem: ToolbarItem, toolbarId: ToolbarLayoutId) {
    Box(Modifier.layoutId(toolbarId)) {
        toolbarItem.item()
    }
}

@Composable
private fun Container(type: ToolbarType) {
    Surface(
        modifier = Modifier
            .layoutId(ToolbarLayoutId.CONTAINER)
            .background(Color.Transparent)
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
        color = when (type) {
            is ToolbarType.Light -> AphirriTheme.colors.background
            is ToolbarType.Dark -> AphirriTheme.colors.primary
        }
    ) {}
}

@Composable
private fun ContainerImage(type: ToolbarType) {
    val density = LocalDensity.current
    if (type is ToolbarType.Dark && type.image != null)
        Image(
            painter = painterResource(id = type.image),
            contentDescription = null,
            modifier = Modifier
                .layoutId(ToolbarLayoutId.CONTAINER_IMAGE)
                .alpha(0.25f)
                .fillMaxSize(),
            alignment = { _, _, _ ->
                val xOffset = density.run { 58.dp.toPx() / 2 }.roundToInt()
                IntOffset(x = 0, -500)
            },
            contentScale = ContentScale.Crop,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                setToSaturation(0f)
            }),
        )
}

@Composable
private fun ContainerAnimation() {
    Box(
        modifier = Modifier
            .layoutId(ToolbarLayoutId.CONTAINER_ANIMATION)
            .background(AphirriTheme.colors.primary)
    )
}

@Composable
private fun Shadow() {
    Surface(
        modifier = Modifier
            .layoutId(ToolbarLayoutId.SHADOW)
            .background(Color.Transparent)
            .shadow(
                elevation = AphirriTheme.elevation.toolbar.elevation,
                shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
                ambientColor = AphirriTheme.elevation.toolbar.ambientColor,
                spotColor = AphirriTheme.elevation.toolbar.spotColor,
            )
            .clip(RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)),
        color = AphirriTheme.colors.background
    ) {}
}

private fun startConstraintSet() = ConstraintSet {
    refs {
        constrain(slogan) {
            height = Dimension.wrapContent
            start.linkTo(container.start)
            end.linkTo(container.end)
            top.linkTo(container.top)
        }

        constrain(containerAnimation) {
            width = Dimension.fillToConstraints
            height = Dimension.value(48.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(container.bottom, (-24).dp)
        }

        constrain(container) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(caption.bottom, (-12).dp)
        }

        constrain(containerImage) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            start.linkTo(container.start)
            end.linkTo(container.end)
            top.linkTo(container.top)
            bottom.linkTo(container.bottom, (-24).dp)
        }

        constrain(shadow) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(container.bottom)
            alpha = 0f
        }

        constrain(title) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(slogan.bottom)
            scaleX = 1f
            scaleY = 1f
        }

        constrain(caption) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(title.bottom)
            scaleX = 1f
        }

        constrain(content) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(container.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
    }
}

private fun endConstraintSet(aphirriToolbarContent: AphirriToolbarContent) = ConstraintSet {
    refs {
        constrain(slogan) {
            height = Dimension.wrapContent
            start.linkTo(container.start)
            end.linkTo(container.end)
            top.linkTo(container.top)
        }

        constrain(containerAnimation) {
            width = Dimension.fillToConstraints
            height = Dimension.value(48.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(container.bottom, (-72).dp)
            alpha = 0f
        }

        constrain(containerImage) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            start.linkTo(container.start)
            end.linkTo(container.end)
            top.linkTo(container.top)
            bottom.linkTo(container.bottom, (-24).dp)
            alpha = 0f
        }

        constrain(container) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(title.bottom, (-12).dp)
        }

        constrain(shadow) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(container.bottom)
            alpha = 1f
        }

        constrain(title) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(slogan.bottom, aphirriToolbarContent.title.offsetY)
            scaleX = aphirriToolbarContent.title.endScale
            scaleY = aphirriToolbarContent.title.endScale
        }

        constrain(caption) {
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            top.linkTo(slogan.bottom)
            scaleX = 0.3f
            scaleY = 0.3f
            alpha = 0f
        }

        constrain(content) {
            width = Dimension.fillToConstraints
            height = Dimension.fillToConstraints
            top.linkTo(container.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
private fun createTransition() = Transition {
    refs {
        keyAttributes(caption) {
            frame(25) {
                alpha = 0f
            }
        }

        keyAttributes(containerAnimation) {
            frame(50) {
                alpha = 1f
            }
        }
    }
}