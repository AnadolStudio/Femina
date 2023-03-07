package com.anadolstudio.femina.utils.animate

import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.Animation

object AnimateUtil {

    private const val BASE_BLINK_DURATION = 1000L

    fun blinkAnimation(duration: Long = BASE_BLINK_DURATION): Animation = AlphaAnimation(1f, 0f).apply {
        this.duration = duration
        interpolator = AccelerateDecelerateInterpolator()
        repeatCount = Animation.INFINITE
        repeatMode = Animation.REVERSE
    }

}
