package com.sucho.data.datasource

import com.sucho.domain.responemodels.TestResponse

interface TestDataSource {
  suspend fun getTestData(): TestResponse
}