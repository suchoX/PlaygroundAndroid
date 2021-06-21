package com.sucho.data.repository

import com.sucho.data.datasource.KanyeQuotesDataSource
import com.sucho.data.remote.SafeResult
import com.sucho.data.remote.SafeResult.Failure
import com.sucho.data.remote.SafeResult.NetworkError
import com.sucho.data.remote.SafeResult.Success
import com.sucho.domain.mappers.toKanyeQuote
import com.sucho.domain.model.KanyeQuote
import com.sucho.domain.responemodels.KanyeQuoteResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KanyeQuotesRepo @Inject constructor(
  private val kanyeQuotesDataSource: KanyeQuotesDataSource
) {

  val kanyeQuotes: Flow<SafeResult<KanyeQuote>> =
    kanyeQuotesDataSource.kanyeQuotes
      .map{ result ->
        when (result) {
          is Success -> Success(result.data.toKanyeQuote())
          is NetworkError -> result
          is Failure -> result
        }
      }
}