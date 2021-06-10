package com.sucho.playground.injection

import com.sucho.data.datasource.TestDataSource
import com.sucho.playground.datasource.TestDataSourceImpl
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
  abstract fun bindTestDataSource(testDataSourceImpl: TestDataSourceImpl): TestDataSource
}