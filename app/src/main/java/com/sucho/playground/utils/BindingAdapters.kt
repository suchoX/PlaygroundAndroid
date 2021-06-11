package com.sucho.playground.utils

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.BindingAdapter


@BindingAdapter(
  "paddingLeftSystemWindowInsets",
  "paddingTopSystemWindowInsets",
  "paddingRightSystemWindowInsets",
  "paddingBottomSystemWindowInsets",
  requireAll = false
)
fun View.applySystemWindowInsetsPadding(
  previousApplyLeft: Boolean,
  previousApplyTop: Boolean,
  previousApplyRight: Boolean,
  previousApplyBottom: Boolean,
  applyLeft: Boolean,
  applyTop: Boolean,
  applyRight: Boolean,
  applyBottom: Boolean
) {
  if (previousApplyLeft == applyLeft &&
    previousApplyTop == applyTop &&
    previousApplyRight == applyRight &&
    previousApplyBottom == applyBottom
  ) {
    return
  }

  doOnApplyWindowInsets { view, insets, padding, _ ->
    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
    val left = if (applyLeft) systemBars.left else 0
    val top = if (applyTop) systemBars.top else 0
    val right = if (applyRight) systemBars.right else 0
    val bottom = if (applyBottom) systemBars.bottom else 0

    view.setPadding(
      padding.left + left,
      padding.top + top,
      padding.right + right,
      padding.bottom + bottom
    )
  }
}



fun View.doOnApplyWindowInsets(
  block: (View, WindowInsetsCompat, InitialPadding, InitialMargin) -> Unit
) {
  // Create a snapshot of the view's padding & margin states
  val initialPadding = recordInitialPaddingForView(this)
  val initialMargin = recordInitialMarginForView(this)
  // Set an actual OnApplyWindowInsetsListener which proxies to the given
  // lambda, also passing in the original padding & margin states
  ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
    block(v, insets, initialPadding, initialMargin)
    // Always return the insets, so that children can also use them
    insets
  }
  // request some insets
  requestApplyInsetsWhenAttached()
}

private fun recordInitialPaddingForView(view: View) = InitialPadding(
  view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom
)

private fun recordInitialMarginForView(view: View): InitialMargin {
  val lp = view.layoutParams as? ViewGroup.MarginLayoutParams
    ?: throw IllegalArgumentException("Invalid view layout params")
  return InitialMargin(lp.leftMargin, lp.topMargin, lp.rightMargin, lp.bottomMargin)
}

fun View.requestApplyInsetsWhenAttached() {
  if (isAttachedToWindow) {
    // We're already attached, just request as normal
    requestApplyInsets()
  } else {
    // We're not attached to the hierarchy, add a listener to
    // request when we are
    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
      override fun onViewAttachedToWindow(v: View) {
        v.removeOnAttachStateChangeListener(this)
        v.requestApplyInsets()
      }

      override fun onViewDetachedFromWindow(v: View) = Unit
    })
  }
}


class InitialPadding(val left: Int, val top: Int, val right: Int, val bottom: Int)

class InitialMargin(val left: Int, val top: Int, val right: Int, val bottom: Int)