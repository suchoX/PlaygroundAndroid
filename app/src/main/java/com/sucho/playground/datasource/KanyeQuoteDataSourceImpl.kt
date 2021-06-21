package com.sucho.playground.datasource

import com.sucho.data.datasource.KanyeQuotesDataSource
import com.sucho.data.remote.KanyeQuoteApiService
import com.sucho.data.remote.SafeResult
import com.sucho.data.remote.safeApiCall
import com.sucho.domain.responemodels.KanyeQuoteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KanyeQuoteDataSourceImpl @Inject constructor(
  private val kanyeQuoteApiService: KanyeQuoteApiService
  ): KanyeQuotesDataSource {

  override val kanyeQuotes: Flow<SafeResult<KanyeQuoteResponse>>
    get() = flow {
      while(true) {
        val result = safeApiCall(Dispatchers.IO) {
          kanyeQuoteApiService.getFiveRandomJokes()
        }
        emit(result)
        delay(3000)
      }
    }
}