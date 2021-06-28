package com.sucho.data.usecase

import com.sucho.data.db.AppDatabase
import com.sucho.data.db.entity.QuoteEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveQuoteUseCase @Inject constructor(
  private val appDatabase: AppDatabase
) : BaseUseCase<Unit, QuoteEntity> {

  override suspend fun perform(quoteEntity: QuoteEntity): Unit {
    return appDatabase.quotesDao().saveQuote(quoteEntity)
  }
}