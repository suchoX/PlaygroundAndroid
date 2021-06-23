package com.sucho.playground.datasource

import com.sucho.data.datasource.SwansonImageDataSource
import com.sucho.playground.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SwansonImageDataSourceImpl @Inject constructor(): SwansonImageDataSource {
  override val swansonImages: Flow<Int>
    get() = flow {
      while (true) {
        when ((1..10).random()) {
          1 -> emit(R.drawable.ron1)
          2 -> emit(R.drawable.ron2)
          3 -> emit(R.drawable.ron3)
          4 -> emit(R.drawable.ron4)
          5 -> emit(R.drawable.ron5)
          6 -> emit(R.drawable.ron6)
          7 -> emit(R.drawable.ron7)
          8 -> emit(R.drawable.ron8)
          9 -> emit(R.drawable.ron9)
          10 -> emit(R.drawable.ron10)
        }
      }
    }
}