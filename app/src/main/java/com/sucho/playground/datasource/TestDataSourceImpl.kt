package com.sucho.playground.datasource

import com.sucho.data.datasource.TestDataSource
import com.sucho.domain.responemodels.TestResponse
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestDataSourceImpl @Inject constructor() : TestDataSource {
  override suspend fun getTestData(): TestResponse {
    delay(1500)
    return TestResponse(100)
  }
}