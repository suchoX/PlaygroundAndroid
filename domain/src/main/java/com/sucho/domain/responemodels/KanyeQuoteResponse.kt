package com.sucho.domain.responemodels

import com.google.gson.annotations.SerializedName

data class KanyeQuoteResponse(
  @SerializedName("quote")
  val quote: String
)