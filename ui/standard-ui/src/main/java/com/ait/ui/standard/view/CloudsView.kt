package com.ait.ui.standard.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.ait.ui.standard.R
import kotlin.math.pow
import kotlin.math.sin
import kotlin.random.Random

class CloudsView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val cloudDrawable =
        AppCompatResources.getDrawable(context, R.drawable.cloud).apply {
            alpha = 0.9f
        } ?: throw NullPointerException()

    private val viewBoundsRect: Rect = Rect()
    private val cloudSize: MutableSize = MutableSize(0, 0)

    private val cloudsBounds = mutableListOf<VisibleRect>()

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        viewBoundsRect.set(left, top, right, bottom)
        val height = (bottom - top) * CLOUD_HEIGHT_SIZE_RATIO
        val width = (height / cloudDrawable.intrinsicHeight) * cloudDrawable.intrinsicWidth
        cloudSize.width = width.toInt()
        cloudSize.height = height.toInt()

        cloudsBounds.addAll(createCloudsSet())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        cloudsBounds.forEach { cloudBounds ->
            if (cloudBounds.visible) {
                cloudDrawable.bounds = cloudBounds.rect
                cloudDrawable.draw(canvas)
            }
        }
    }

    var clouds: Int = 0
        set(value) {
            field = value
            recalculateVisibility()
            invalidate()
        }

    private fun createCloudsSet(): List<VisibleRect> {
        val resultList = mutableListOf<VisibleRect>()
        val random = Random(222)

        val amplitude = height * 0.4f
        val heightDelta = height * 0.35f
        var i = 0
        while (i < width + 10) {
            val wx = i.toFloat()
            val wy =
                amplitude * 2 + amplitude * sin((i + 10) * Math.PI / 30).toFloat() - heightDelta
            random.nextInt(0, RANDOM_COEFF).takeIf { it == 0 }?.let {
                resultList.add(
                    VisibleRect(
                        rect = Rect(
                            (wx - cloudSize.width / 2).toInt(),
                            (wy - cloudSize.height / 2).toInt(),
                            (wx + cloudSize.width / 2).toInt(),
                            (wy + cloudSize.height / 2).toInt(),
                        ),
                        visible = false
                    )
                )

            }
            i += 1
        }
        return resultList
    }

    private fun recalculateVisibility() {
        val random = Random(10)
        val count = (clouds.toDouble() * cloudsBounds.size.toDouble() / 100).exp()
        val visibleClouds = (0..cloudsBounds.size).shuffled(random).take(count.toInt())
        cloudsBounds.forEachIndexed { index, rect ->
            rect.visible = visibleClouds.contains(index)
        }
    }

    private fun Double.exp(): Double = (1.05.pow(0.4 * this) - 1) * 100 / 6

    companion object {

        private const val CLOUD_HEIGHT_SIZE_RATIO = 0.2
        private const val RANDOM_COEFF = 9
    }

    private data class MutableSize(
        var width: Int,
        var height: Int
    )

    private data class VisibleRect(
        val rect: Rect,
        var visible: Boolean,
    )
}