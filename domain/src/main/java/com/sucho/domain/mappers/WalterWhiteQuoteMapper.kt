package com.sucho.domain.mappers

import com.sucho.domain.model.WalterWhiteQuote
import com.sucho.domain.responemodels.WalterWhiteQuoteResponse

fun WalterWhiteQuoteResponse.toWalterWhiteQuote(): WalterWhiteQuote {
  return WalterWhiteQuote(this.quote)
}