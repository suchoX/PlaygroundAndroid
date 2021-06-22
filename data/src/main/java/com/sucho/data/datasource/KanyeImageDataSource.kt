package com.sucho.data.datasource

import kotlinx.coroutines.flow.Flow

interface KanyeImageDataSource {
  val kanyeImages: Flow<Int>
}