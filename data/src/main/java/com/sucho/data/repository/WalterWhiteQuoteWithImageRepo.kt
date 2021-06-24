package com.sucho.data.repository

import com.sucho.data.datasource.WalterWhiteQuotesDataSource
import com.sucho.data.remote.SafeResult.Success
import com.sucho.domain.mappers.toWalterWhiteQuote
import com.sucho.domain.model.WalterWhiteQuoteWithImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalterWhiteQuoteWithImageRepo @Inject constructor(
  private val walterWhiteQuotesDataSource: WalterWhiteQuotesDataSource
) {
  val walterWhiteQuotes: Flow<WalterWhiteQuoteWithImage> =
    walterWhiteQuotesDataSource.walterWhiteQuotes
      .filter { result -> result is Success }
      .map { result -> result as Success }
      .map { result -> result.data[0].toWalterWhiteQuote() }
      .map { result -> WalterWhiteQuoteWithImage(result.quote, 1) }
}