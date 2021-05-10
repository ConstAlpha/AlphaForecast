package com.ait.ui.common

import android.view.View
import androidx.core.view.ViewCompat

private const val FLAG_INSET_LEFT = 1
private const val FLAG_INSET_TOP = 2
private const val FLAG_INSET_RIGHT = 4
private const val FLAG_INSET_BOTTOM = 8

fun View.fullscreenInsetTop() = insetShifts(
    flags = FLAG_INSET_TOP,
    top = paddingTop
)

fun View.fullscreenInsetBottom() = insetShifts(
    flags = FLAG_INSET_BOTTOM,
    bottom = paddingBottom
)

private fun View.insetShifts(
    flags: Int,
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0,
) {
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val insetLeft = if (flags and FLAG_INSET_LEFT != 0) insets.systemWindowInsetLeft else 0
        val insetTop = if (flags and FLAG_INSET_TOP != 0) insets.systemWindowInsetTop else 0
        val insetRight = if (flags and FLAG_INSET_RIGHT != 0) insets.systemWindowInsetRight else 0
        val insetBottom = if (flags and FLAG_INSET_BOTTOM != 0) insets.systemWindowInsetBottom else 0
        view.setPadding(
            insetLeft + left,
            insetTop + top,
            insetRight + right,
            insetBottom + bottom
        )
        insets
    }
}