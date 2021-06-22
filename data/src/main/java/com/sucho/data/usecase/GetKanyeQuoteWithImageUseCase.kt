package com.sucho.data.usecase

import com.sucho.data.remote.SafeResult
import com.sucho.data.repository.KanyeQuoteWithImageRepo
import com.sucho.domain.model.KanyeQuote
import com.sucho.domain.model.KanyeQuoteWithImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetKanyeQuoteWithImageUseCase @Inject constructor(
  private val kanyeQuoteWithImageRepo: KanyeQuoteWithImageRepo
) : BaseUseCase<Flow<KanyeQuoteWithImage>, Unit> {

  override suspend fun perform(): Flow<KanyeQuoteWithImage> {
    return kanyeQuoteWithImageRepo.kanyeQuotes
  }
}