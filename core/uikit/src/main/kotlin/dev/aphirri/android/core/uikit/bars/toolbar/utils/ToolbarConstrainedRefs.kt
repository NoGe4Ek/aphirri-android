package dev.aphirri.android.core.uikit.bars.toolbar.utils

import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintSetScope
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.TransitionScope

internal data class ToolbarConstrainedRefs(
    val containerAnimation: ConstrainedLayoutReference,
    val containerImage: ConstrainedLayoutReference,
    val container: ConstrainedLayoutReference,
    val shadow: ConstrainedLayoutReference,
    val slogan: ConstrainedLayoutReference,
    val title: ConstrainedLayoutReference,
    val caption: ConstrainedLayoutReference,
    val content: ConstrainedLayoutReference
) {
    companion object {
        private fun commonRefs(creator: (ToolbarLayoutId) -> ConstrainedLayoutReference) =
            ToolbarConstrainedRefs(
                containerAnimation = creator(ToolbarLayoutId.CONTAINER_ANIMATION),
                containerImage = creator(ToolbarLayoutId.CONTAINER_IMAGE),
                slogan = creator(ToolbarLayoutId.SLOGAN),
                container = creator(ToolbarLayoutId.CONTAINER),
                shadow = creator(ToolbarLayoutId.SHADOW),
                title = creator(ToolbarLayoutId.TITLE),
                caption = creator(ToolbarLayoutId.CAPTION),
                content = creator(ToolbarLayoutId.CONTENT)
            )

        fun ConstraintSetScope.refs(block: ToolbarConstrainedRefs.() -> Unit) {
            val refs = commonRefs(::createRefFor)
            refs.block()
        }

        @OptIn(ExperimentalMotionApi::class)
        fun TransitionScope.refs(block: ToolbarConstrainedRefs.() -> Unit) {
            val refs = commonRefs(::createRefFor)
            refs.block()
        }
    }
}