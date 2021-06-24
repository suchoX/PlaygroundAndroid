package com.sucho.data.datasource

import com.sucho.data.remote.SafeResult
import com.sucho.domain.responemodels.WalterWhiteQuoteResponse
import kotlinx.coroutines.flow.Flow

interface WalterWhiteQuotesDataSource {
  val walterWhiteQuotes: Flow<SafeResult<List<WalterWhiteQuoteResponse>>>
}