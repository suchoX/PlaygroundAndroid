package com.sucho.data.usecase

import com.sucho.data.repository.WalterWhiteQuoteWithImageRepo
import com.sucho.domain.model.WalterWhiteQuoteWithImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetWalterWhiteQuoteWithImageUseCase @Inject constructor(
  private val walterWhiteQuoteWithImageRepo: WalterWhiteQuoteWithImageRepo
): BaseUseCase<Flow<WalterWhiteQuoteWithImage>, Unit> {
  override suspend fun perform(): Flow<WalterWhiteQuoteWithImage> {
    return walterWhiteQuoteWithImageRepo.walterWhiteQuotes
  }
}