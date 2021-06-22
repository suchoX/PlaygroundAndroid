package com.sucho.data.usecase

import android.util.Log
import com.sucho.data.repository.SwansonQuoteRepo
import com.sucho.domain.model.SwansonQuoteWithImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSwansonQuoteUseCase @Inject constructor(
  private val swansonQuoteRepo: SwansonQuoteRepo
) : BaseUseCase<Flow<SwansonQuoteWithImage>, Unit> {
  override suspend fun perform(): Flow<SwansonQuoteWithImage> {
    return swansonQuoteRepo.swansonQuote
  }
}