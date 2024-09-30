package dev.aphirri.android.ui.splash

import android.animation.ObjectAnimator
import android.app.Activity
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

fun Activity.setupSplashScreen() {
    installSplashScreen().apply {
        setOnExitAnimationListener { viewProvider ->
            ObjectAnimator.ofFloat(
                viewProvider.iconView,
                "scaleX",
                0.3f
            ).apply {
                interpolator = AnticipateInterpolator(2.5f)
                duration = 250
                doOnEnd { viewProvider.remove() }
                start()
            }
            ObjectAnimator.ofFloat(
                viewProvider.iconView,
                "scaleY",
                0.3f
            ).apply {
                interpolator = AnticipateInterpolator(2.5f)
                duration = 250
                doOnEnd { viewProvider.remove() }
                start()
            }
        }
    }
}