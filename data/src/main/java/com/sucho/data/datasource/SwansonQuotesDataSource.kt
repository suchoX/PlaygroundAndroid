package com.sucho.data.datasource

import com.sucho.data.remote.SafeResult
import kotlinx.coroutines.flow.Flow

interface SwansonQuotesDataSource {
  val swansonQuotes: Flow<SafeResult<List<String>>>
}