package com.sucho.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sucho.data.db.dao.QuotesDao
import com.sucho.data.db.entity.QuoteEntity

@Database(entities = [QuoteEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
  companion object {
    const val APP_DATABASE_NAME = "playground-database"
  }

  abstract fun quotesDao(): QuotesDao
}