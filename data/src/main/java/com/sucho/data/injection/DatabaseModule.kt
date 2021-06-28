package com.sucho.data.injection

import android.content.Context
import androidx.room.Room
import com.sucho.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

  @Provides
  @Singleton
  internal fun provideDatabase(
    @ApplicationContext context: Context,
  ): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java,"default-db")
      .build()
  }
}