package com.ait.ui.standard.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.ait.ui.common.TimeDescriptor
import com.ait.ui.common.TimeOfDay
import com.ait.ui.standard.R


class WeatherView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val dayBackgroundDrawable =
        AppCompatResources.getDrawable(context, R.drawable.day) ?: throw NullPointerException()
    private val nightBackgroundDrawable =
        AppCompatResources.getDrawable(context, R.drawable.night) ?: throw NullPointerException()
    private val sunriseBackgroundDrawable =
        AppCompatResources.getDrawable(context, R.drawable.sunrise) ?: throw NullPointerException()
    private val sunsetBackgroundDrawable =
        AppCompatResources.getDrawable(context, R.drawable.sunset) ?: throw NullPointerException()

    private val backgroundDrawable = SwappableDrawable(dayBackgroundDrawable, this)

    private var backgroundBoundsRect = Rect()

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        backgroundBoundsRect.set(left, top, right, bottom)
        dayBackgroundDrawable.bounds = backgroundBoundsRect
        nightBackgroundDrawable.bounds = backgroundBoundsRect
        sunriseBackgroundDrawable.bounds = backgroundBoundsRect
        sunsetBackgroundDrawable.bounds = backgroundBoundsRect
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        backgroundDrawable.draw(canvas)
    }

    fun update(timeDescriptor: TimeDescriptor?) {
        timeDescriptor?.run {
            updateDayTime(timeOfDay)
        }
    }

    private fun updateDayTime(dayTime: TimeOfDay) {
        backgroundDrawable.swap(dayTime.drawable)
    }

    private val TimeOfDay.drawable: Drawable
        get() =
            when (this) {
                TimeOfDay.NIGHT -> nightBackgroundDrawable
                TimeOfDay.MORNING -> sunriseBackgroundDrawable
                TimeOfDay.DAY -> dayBackgroundDrawable
                TimeOfDay.EVENING -> sunsetBackgroundDrawable
            }

    private class SwappableDrawable(
        initDrawable: Drawable,
        private val parentView: View,
    ) {
        private var oldDrawable: Drawable? = null
        private var currentDrawable: Drawable = initDrawable

        fun swap(newDrawable: Drawable) {
            if (currentDrawable == newDrawable) return
            oldDrawable = currentDrawable
            currentDrawable = newDrawable

            startSwapAnimation()

            parentView.invalidate()
        }

        fun draw(canvas: Canvas) {
            oldDrawable?.draw(canvas)
            currentDrawable.draw(canvas)
        }

        private fun startSwapAnimation() {
            oldDrawable?.alpha = ALPHA_MAX_VALUE
            currentDrawable.alpha = ALPHA_MIN_VALUE

            ValueAnimator.ofInt(ALPHA_MIN_VALUE, ALPHA_MAX_VALUE).apply {
                duration = ANIMATION_DURATION
                addUpdateListener {
                    (it.animatedValue as Int).let { value ->
                        changeDrawablesAlpha(value)
                        if (value == ALPHA_MAX_VALUE) {
                            oldDrawable = null
                        }
                    }
                }
                start()
            }
        }

        private fun changeDrawablesAlpha(newValue: Int) {
            currentDrawable.alpha = newValue

            parentView.invalidate()
        }

        companion object {
            private const val ANIMATION_DURATION = 250L

            private const val ALPHA_MIN_VALUE = 0
            private const val ALPHA_MAX_VALUE = 255
        }
    }
}