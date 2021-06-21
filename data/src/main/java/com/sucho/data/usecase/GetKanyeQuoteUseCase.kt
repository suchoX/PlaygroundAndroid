package com.sucho.data.usecase

import com.sucho.data.remote.SafeResult
import com.sucho.data.repository.KanyeQuotesRepo
import com.sucho.domain.model.KanyeQuote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetKanyeQuoteUseCase @Inject constructor(
  private val kanyeQuotesRepo: KanyeQuotesRepo
) : BaseUseCase<Flow<SafeResult<KanyeQuote>>, Unit> {

  override suspend fun perform(): Flow<SafeResult<KanyeQuote>> {
    return kanyeQuotesRepo.kanyeQuotes
  }
}