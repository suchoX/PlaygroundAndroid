package com.sucho.domain.model

class WalterWhiteQuoteWithImage constructor(
  val quote: String,
  val imageResId: Int
) {
  companion object {
    fun getType() = 3
  }
}
