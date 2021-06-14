package com.sucho.playground.injection

import com.sucho.data.datasource.KanyeQuotesDataSource
import com.sucho.playground.datasource.KanyeQuoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

  @Binds
  @Singleton
  abstract fun binKanyeQuoteDataSource(kanyeQuoteDataSourceImpl: KanyeQuoteDataSourceImpl): KanyeQuotesDataSource
}