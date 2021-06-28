package com.sucho.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sucho.data.db.entity.QuoteEntity

@Dao
interface QuotesDao {
  @Query("SELECT * from quoteentity")
  fun getAll(): List<QuoteEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveQuote(quoteEntity: QuoteEntity)
}