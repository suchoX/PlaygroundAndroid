package com.sucho.data.datasource

import kotlinx.coroutines.flow.Flow

interface SwansonImageDataSource {
  val swansonImages: Flow<Int>
}