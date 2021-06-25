package com.sucho.playground.utils

import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.smoothScrollToPositionWithSpeed(
  position: Int,
  millisPerInch: Float = 100f
) {
  val lm = requireNotNull(layoutManager)
  val scroller = object : LinearSmoothScroller(context) {
    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
      return millisPerInch / displayMetrics.densityDpi
    }
  }
  scroller.targetPosition = position
  lm.startSmoothScroll(scroller)
}