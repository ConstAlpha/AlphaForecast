package com.ait.ui.standard.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.content.res.AppCompatResources
import com.ait.ui.standard.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class CelestialView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val viewBounds = Rect()
    private val boundsRect = Rect()

    private var drawable: Drawable? = null

    private var size: Int = 0
    private var position: Double = 0.0

    init {
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.CelestialView,
            0, 0
        ).apply {
            val drawableResource = getResourceId(R.styleable.CelestialView_drawable, 0)
            if (drawableResource != 0) {
                drawable = AppCompatResources.getDrawable(context, drawableResource)
            }
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        updateLayout(left, top, right, bottom)
    }

    private fun updateLayout(left: Int, top: Int, right: Int, bottom: Int) {
        viewBounds.set(left, top, right, bottom)
        updateBounds()
    }

    private fun updateBounds() {
        size = ((viewBounds.right - viewBounds.left) * SIZE_RATIO).toInt()

        val positionPercent = position / 100.0
        val startValue = PI / 4
        val endValue = 3 * PI / 4
        val delta = endValue - startValue
        val rad = startValue + delta * positionPercent

        val horizontalRadius = (viewBounds.right + size - viewBounds.left) / 2 + size
        val horizontalPosition = (horizontalRadius + horizontalRadius * cos(rad) - 2 * size).toInt()

        val verticalRadius = (viewBounds.bottom - viewBounds.top)
        val verticalPosition =
            (verticalRadius - verticalRadius * sin(rad)).toInt() + verticalRadius / 6

        boundsRect.set(
            horizontalPosition,
            verticalPosition,
            horizontalPosition + size,
            verticalPosition + size
        )
        drawable?.bounds = boundsRect
    }

    override fun onDraw(canvas: Canvas) {
        drawable?.draw(canvas)
    }

    fun updateCelestialPosition(percentPosition: Int) {
        // From current to new
        move(position.toInt(), percentPosition)
    }

    private fun updatePosition(x: Double) {
        position = x
        updateBounds()
        invalidate()
    }

    private fun move(from: Int, to: Int) {
        ValueAnimator.ofFloat(from.toFloat(), to.toFloat()).apply {
            duration = ANIMATION_DURATION
            interpolator = LinearInterpolator()
            addUpdateListener {
                updatePosition((it.animatedValue as Float).toDouble())
            }
            start()
        }
    }

    companion object {
        private const val ANIMATION_DURATION = 500L
        private const val SIZE_RATIO = 0.25
    }
}