package com.sucho.playground.injection

import android.content.Context
import androidx.work.WorkManager
import com.sucho.data.datasource.KanyeQuotesDataSource
import com.sucho.playground.datasource.KanyeQuoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
  @Provides
  @Singleton
  fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
    return WorkManager.getInstance(context)
  }
}