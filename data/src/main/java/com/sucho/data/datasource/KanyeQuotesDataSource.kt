package com.sucho.data.datasource

import com.sucho.data.remote.SafeResult
import com.sucho.domain.responemodels.KanyeQuoteResponse
import kotlinx.coroutines.flow.Flow

interface KanyeQuotesDataSource {
  val kanyeQuotes: Flow<SafeResult<KanyeQuoteResponse>>
}