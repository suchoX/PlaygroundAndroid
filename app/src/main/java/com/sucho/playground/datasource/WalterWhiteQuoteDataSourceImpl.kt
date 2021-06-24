package com.sucho.playground.datasource

import com.sucho.data.datasource.WalterWhiteQuotesDataSource
import com.sucho.data.remote.SafeResult
import com.sucho.data.remote.WalterWhiteQuoteApiService
import com.sucho.data.remote.safeApiCall
import com.sucho.domain.responemodels.WalterWhiteQuoteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalterWhiteQuoteDataSourceImpl @Inject constructor(
  private val walterWhiteQuoteApiService: WalterWhiteQuoteApiService
) : WalterWhiteQuotesDataSource {
  override val walterWhiteQuotes: Flow<SafeResult<List<WalterWhiteQuoteResponse>>>
    get() = flow {
      while (true) {
        val result = safeApiCall(Dispatchers.IO) {
          walterWhiteQuoteApiService.getWalterWhiteQuote()
        }
        emit(result)
        delay(6000)
      }
    }
}