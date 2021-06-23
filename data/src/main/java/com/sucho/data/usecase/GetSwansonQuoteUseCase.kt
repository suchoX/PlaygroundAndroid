package com.sucho.data.usecase

import com.sucho.data.repository.SwansonQuoteWithImageRepo
import com.sucho.domain.model.SwansonQuoteWithImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSwansonQuoteUseCase @Inject constructor(
  private val swansonQuoteWithImageRepo: SwansonQuoteWithImageRepo
) : BaseUseCase<Flow<SwansonQuoteWithImage>, Unit> {
  override suspend fun perform(): Flow<SwansonQuoteWithImage> {
    return swansonQuoteWithImageRepo.swansonQuote
  }
}