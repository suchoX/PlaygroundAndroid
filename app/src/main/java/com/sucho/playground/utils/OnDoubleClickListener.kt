package com.sucho.playground.utils

import android.os.Handler
import android.view.View

open class OnDoubleClickListener(
  private val customClickListener: CustomClickListener,
  private val interval: Long = 200L
) : View.OnClickListener {
  private val handler = Handler()
  private var counterClicks = 0
  private var isBusy = false

  override fun onClick(view: View) {
    if (!isBusy) {
      isBusy = true
      counterClicks++
      handler.postDelayed({
        if (counterClicks >= 2) {
          customClickListener.onDoubleClickEvent(view)
        }
        if (counterClicks == 1) {
          customClickListener.onSingleClickEvent(view)
        }

        counterClicks = 0
      }, interval)
      isBusy = false
    }
  }
}

interface CustomClickListener {
  fun onSingleClickEvent(view: View?)
  fun onDoubleClickEvent(view: View?)
}

