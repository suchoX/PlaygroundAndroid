package com.sucho.data.repository

import com.sucho.data.datasource.WalterWhiteImageDataSource
import com.sucho.data.datasource.WalterWhiteQuotesDataSource
import com.sucho.data.remote.SafeResult.Success
import com.sucho.domain.mappers.toWalterWhiteQuote
import com.sucho.domain.model.WalterWhiteQuoteWithImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalterWhiteQuoteWithImageRepo @Inject constructor(
  private val walterWhiteQuotesDataSource: WalterWhiteQuotesDataSource,
  private val walterWhiteImageDataSource: WalterWhiteImageDataSource
) {
  val walterWhiteQuotes: Flow<WalterWhiteQuoteWithImage> =
    walterWhiteQuotesDataSource.walterWhiteQuotes
      .filter { result -> result is Success }
      .map { result -> result as Success }
      .map { result -> result.data[0].toWalterWhiteQuote() }
      .zip(walterWhiteImageDataSource.walterWhiteImages) { walterWhiteQuote, walterWhiteImage ->
        WalterWhiteQuoteWithImage(walterWhiteQuote.quote, walterWhiteImage)
      }
}