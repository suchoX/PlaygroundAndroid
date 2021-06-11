package com.sucho.playground.datasource

import com.sucho.data.datasource.KanyeQuotesDataSource
import com.sucho.data.remote.KanyeQuoteApiService
import com.sucho.data.remote.SafeResult
import com.sucho.data.remote.safeApiCall
import com.sucho.domain.responemodels.KanyeQuoteResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KanyeQuoteDataSourceImpl @Inject constructor(
  private val kanyeQuoteApiService: KanyeQuoteApiService
  ): KanyeQuotesDataSource {
  override suspend fun getKanyeQuote(): SafeResult<KanyeQuoteResponse> {
    return safeApiCall(Dispatchers.IO) {
      kanyeQuoteApiService.getFiveRandomJokes()
    }
  }
}