package com.sucho.playground.datasource

import com.sucho.data.datasource.KanyeImageDataSource
import com.sucho.playground.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KanyeImageDataSourceImpl @Inject constructor() : KanyeImageDataSource {
  override val kanyeImages: Flow<Int>
    get() = flow {
      while (true) {
        when ((1..6).random()) {
          1 -> emit(R.drawable.kanye1)
          2 -> emit(R.drawable.kanye2)
          3 -> emit(R.drawable.kanye3)
          4 -> emit(R.drawable.kanye4)
          5 -> emit(R.drawable.kanye5)
          6 -> emit(R.drawable.kanye6)
        }
        delay(6000)
      }
    }
}