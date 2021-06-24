package com.sucho.data.datasource

import kotlinx.coroutines.flow.Flow

interface WalterWhiteImageDataSource {
  val walterWhiteImages: Flow<Int>
}