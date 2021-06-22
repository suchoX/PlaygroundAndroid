package com.sucho.playground.datasource

import android.util.Log
import com.sucho.data.datasource.SwansonQuotesDataSource
import com.sucho.data.remote.SafeResult
import com.sucho.data.remote.SwansonQuoteApiService
import com.sucho.data.remote.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SwansonQuoteDataSourceImpl @Inject constructor(
  private val swansonQuoteApiService: SwansonQuoteApiService
) : SwansonQuotesDataSource {
  override val swansonQuotes: Flow<SafeResult<List<String>>>
    get() = flow {
      while (true) {
        val result = safeApiCall(Dispatchers.IO) {
          swansonQuoteApiService.getSwansonQuote()
        }
        emit(result)
        delay(6000)
      }
    }
}