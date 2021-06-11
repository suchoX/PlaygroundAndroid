package com.sucho.data.datasource

import com.sucho.data.remote.SafeResult
import com.sucho.domain.responemodels.KanyeQuoteResponse

interface KanyeQuotesDataSource {
  suspend fun getKanyeQuote(): SafeResult<KanyeQuoteResponse>
}