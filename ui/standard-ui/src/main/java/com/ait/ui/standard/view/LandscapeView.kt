package com.ait.ui.standard.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.ait.ui.standard.R

class LandscapeView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val landscapeDrawable =
        AppCompatResources.getDrawable(context, R.drawable.landscape)
            ?: throw NullPointerException()

    private var landscapeBoundsRect = Rect()

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        landscapeBoundsRect.set(left, top + (bottom * (1 - LANDSCAPE_RATIO)).toInt(), right, bottom)
        landscapeDrawable.bounds = landscapeBoundsRect
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        landscapeDrawable.draw(canvas)
    }

    companion object {
        private const val LANDSCAPE_RATIO = 0.5
    }
}