package com.sucho.data.repository

import com.sucho.data.datasource.KanyeQuotesDataSource
import com.sucho.data.remote.SafeResult
import com.sucho.domain.responemodels.KanyeQuoteResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KanyeQuotesRepo @Inject constructor(
  private val kanyeQuotesDataSource: KanyeQuotesDataSource
) {
  suspend fun getKanyeQuote() : SafeResult<KanyeQuoteResponse> {
    return kanyeQuotesDataSource.getKanyeQuote()
  }
}