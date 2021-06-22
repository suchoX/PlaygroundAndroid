package com.sucho.data.repository

import com.sucho.data.datasource.KanyeImageDataSource
import com.sucho.data.datasource.KanyeQuotesDataSource
import com.sucho.data.remote.SafeResult
import com.sucho.data.remote.SafeResult.Failure
import com.sucho.data.remote.SafeResult.NetworkError
import com.sucho.data.remote.SafeResult.Success
import com.sucho.domain.mappers.toKanyeQuote
import com.sucho.domain.model.KanyeQuote
import com.sucho.domain.model.KanyeQuoteWithImage
import com.sucho.domain.responemodels.KanyeQuoteResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KanyeQuoteWithImageRepo @Inject constructor(
  private val kanyeQuotesDataSource: KanyeQuotesDataSource,
  private val kanyeImageDataSource: KanyeImageDataSource
) {

  val kanyeQuotes: Flow<KanyeQuoteWithImage> =
    kanyeQuotesDataSource.kanyeQuotes
      .filter { result -> result is Success }
      .map { result -> result as Success }
      .map{ result ->result.data.toKanyeQuote() }
      .zip(kanyeImageDataSource.kanyeImages) { kanyeQuote, kanyeImage ->
        KanyeQuoteWithImage(kanyeQuote.quote, kanyeImage)
      }
}