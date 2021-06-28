package com.sucho.data.usecase

import com.sucho.data.db.AppDatabase
import com.sucho.data.db.entity.QuoteEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetQuotesUseCase @Inject constructor(
  private val appDatabase: AppDatabase
) : BaseUseCase<List<QuoteEntity>, Unit> {

  override suspend fun perform(): List<QuoteEntity> {
    return appDatabase.quotesDao().getAll()
  }
}