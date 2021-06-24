package com.sucho.playground.datasource

import com.sucho.data.datasource.SwansonImageDataSource
import com.sucho.data.datasource.WalterWhiteImageDataSource
import com.sucho.playground.R.drawable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WalterWhiteImageDataSourceImpl @Inject constructor(): WalterWhiteImageDataSource {
  override val walterWhiteImages: Flow<Int>
    get() = flow {
      while (true) {
        when ((1..10).random()) {
          1 -> emit(drawable.ww1)
          2 -> emit(drawable.ww2)
          3 -> emit(drawable.ww3)
          4 -> emit(drawable.ww4)
          5 -> emit(drawable.ww5)
          6 -> emit(drawable.ww6)
          7 -> emit(drawable.ww7)
          8 -> emit(drawable.ww8)
          9 -> emit(drawable.ww9)
          10 -> emit(drawable.ww10)
        }
      }
    }
}