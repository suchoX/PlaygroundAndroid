package com.sucho.data.repository

import com.sucho.data.datasource.TestDataSource
import com.sucho.domain.responemodels.TestResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestRepo @Inject constructor(
  private val testDataSource: TestDataSource
) {
  suspend fun getTestData(): TestResponse {
    return testDataSource.getTestData()
  }
}