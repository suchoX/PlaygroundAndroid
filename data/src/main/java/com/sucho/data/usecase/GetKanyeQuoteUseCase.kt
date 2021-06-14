package com.sucho.data.usecase

import com.sucho.data.remote.SafeResult
import com.sucho.data.remote.SafeResult.Failure
import com.sucho.data.remote.SafeResult.NetworkError
import com.sucho.data.remote.SafeResult.Success
import com.sucho.data.repository.KanyeQuotesRepo
import com.sucho.domain.mappers.toKanyeQuote
import com.sucho.domain.model.KanyeQuote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetKanyeQuoteUseCase @Inject constructor(
  private val kanyeQuotesRepo: KanyeQuotesRepo
) : BaseUseCase<SafeResult<KanyeQuote>, Unit> {

  override suspend fun perform(): SafeResult<KanyeQuote> {
    return when (val result = kanyeQuotesRepo.getKanyeQuote()) {
      is Success -> Success(result.data.toKanyeQuote())
      is NetworkError -> result
      is Failure -> result
    }
  }
}