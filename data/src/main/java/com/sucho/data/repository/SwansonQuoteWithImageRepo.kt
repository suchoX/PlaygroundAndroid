package com.sucho.data.repository

import com.sucho.data.datasource.SwansonImageDataSource
import com.sucho.data.datasource.SwansonQuotesDataSource
import com.sucho.data.remote.SafeResult.Success
import com.sucho.domain.model.SwansonQuoteWithImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SwansonQuoteWithImageRepo @Inject constructor(
  swansonQuotesDataSource: SwansonQuotesDataSource,
  swansonImageDataSource: SwansonImageDataSource
) {
  val swansonQuote: Flow<SwansonQuoteWithImage> =
    swansonQuotesDataSource.swansonQuotes
      .filter { result -> result is Success }
      .map { result -> result as Success }
      .map { result -> result.data}
      .zip(swansonImageDataSource.swansonImages) { swansonQuote, swansonImage ->
        SwansonQuoteWithImage(swansonQuote[0], swansonImage)
      }
}