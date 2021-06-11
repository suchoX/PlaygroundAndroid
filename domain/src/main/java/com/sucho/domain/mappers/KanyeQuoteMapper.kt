package com.sucho.domain.mappers

import com.sucho.domain.model.KanyeQuote
import com.sucho.domain.responemodels.KanyeQuoteResponse

fun KanyeQuoteResponse.toKanyeQuote(): KanyeQuote {
  return KanyeQuote(this.quote)
}