package com.sucho.playground.injection

import com.sucho.data.datasource.KanyeImageDataSource
import com.sucho.data.datasource.KanyeQuotesDataSource
import com.sucho.data.datasource.SwansonImageDataSource
import com.sucho.data.datasource.SwansonQuotesDataSource
import com.sucho.data.datasource.WalterWhiteImageDataSource
import com.sucho.data.datasource.WalterWhiteQuotesDataSource
import com.sucho.playground.datasource.KanyeImageDataSourceImpl
import com.sucho.playground.datasource.KanyeQuoteDataSourceImpl
import com.sucho.playground.datasource.SwansonImageDataSourceImpl
import com.sucho.playground.datasource.SwansonQuoteDataSourceImpl
import com.sucho.playground.datasource.WalterWhiteImageDataSourceImpl
import com.sucho.playground.datasource.WalterWhiteQuoteDataSourceImpl
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
  abstract fun bindKanyeQuoteDataSource(kanyeQuoteDataSourceImpl: KanyeQuoteDataSourceImpl): KanyeQuotesDataSource

  @Binds
  @Singleton
  abstract fun bindKanyeImageDataSource(kanyeImageDataSourceImpl: KanyeImageDataSourceImpl): KanyeImageDataSource

  @Binds
  @Singleton
  abstract fun bindSwansonQuoteDataSource(swansonQuoteDataSourceImpl: SwansonQuoteDataSourceImpl): SwansonQuotesDataSource

  @Binds
  @Singleton
  abstract fun bindSwansonImageDataSource(swansonImageDataSourceImpl: SwansonImageDataSourceImpl): SwansonImageDataSource

  @Binds
  @Singleton
  abstract fun bindWalterWhiteQuoteDataSource(walterWhiteQuoteDataSourceImpl: WalterWhiteQuoteDataSourceImpl): WalterWhiteQuotesDataSource

  @Binds
  @Singleton
  abstract fun bindWalterWhiteImageDataSource(walterWhiteImageDataSourceImpl: WalterWhiteImageDataSourceImpl): WalterWhiteImageDataSource
}