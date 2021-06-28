package com.sucho.domain.model

class KanyeQuoteWithImage constructor(
  val quote: String,
  val imageResId: Int
) {
  companion object {
    fun getType() = 1
  }
}
