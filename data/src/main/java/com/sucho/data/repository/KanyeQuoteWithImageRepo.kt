package com.sucho.data.repository

import com.sucho.data.datasource.KanyeImageDataSource
import com.sucho.data.datasource.KanyeQuotesDataSource
import com.sucho.data.remote.SafeResult.Success
import com.sucho.domain.mappers.toKanyeQuote
import com.sucho.domain.model.KanyeQuoteWithImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KanyeQuoteWithImageRepo @Inject constructor(
  kanyeQuotesDataSource: KanyeQuotesDataSource,
  kanyeImageDataSource: KanyeImageDataSource
) {

  val kanyeQuotes: Flow<KanyeQuoteWithImage> =
    kanyeQuotesDataSource.kanyeQuotes
      .filter { result -> result is Success }
      .map { result -> result as Success }
      .map { result -> result.data.toKanyeQuote() }
      .zip(kanyeImageDataSource.kanyeImages) { kanyeQuote, kanyeImage ->
        KanyeQuoteWithImage(kanyeQuote.quote, kanyeImage)
      }
}