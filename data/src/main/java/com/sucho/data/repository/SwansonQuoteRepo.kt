package com.sucho.data.repository

import com.sucho.data.datasource.SwansonQuotesDataSource
import com.sucho.data.remote.SafeResult.Success
import com.sucho.domain.model.SwansonQuoteWithImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SwansonQuoteRepo @Inject constructor(
  swansonQuotesDataSource: SwansonQuotesDataSource
) {
  val swansonQuote: Flow<SwansonQuoteWithImage> =
    swansonQuotesDataSource.swansonQuotes
      .filter { result -> result is Success }
      .map { result -> result as Success }
      .map { result ->
        SwansonQuoteWithImage(result.data[0], 0)
      }
}