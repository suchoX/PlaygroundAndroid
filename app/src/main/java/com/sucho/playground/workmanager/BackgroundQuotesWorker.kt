package com.sucho.playground.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sucho.data.usecase.GetKanyeQuoteWithImageUseCase
import com.sucho.data.usecase.GetSwansonQuoteUseCase
import com.sucho.data.usecase.GetWalterWhiteQuoteWithImageUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.take

@HiltWorker
class BackgroundQuotesWorker @AssistedInject constructor(
  @Assisted appContext: Context,
  @Assisted workerParameters: WorkerParameters,
  private val kanyeQuoteWithImageUseCase: GetKanyeQuoteWithImageUseCase,
  private val swansonQuoteUseCase: GetSwansonQuoteUseCase,
  private val walterWhiteQuoteWithImageUseCase: GetWalterWhiteQuoteWithImageUseCase
) : CoroutineWorker(appContext, workerParameters) {
  @ExperimentalCoroutinesApi
  override suspend fun doWork(): Result {
    kanyeQuoteWithImageUseCase.perform().flatMapLatest {
      Log.e("1111", it.quote)
      swansonQuoteUseCase.perform()
    }.flatMapLatest {
      Log.e("2222", it.quote)
      walterWhiteQuoteWithImageUseCase.perform()
    }.take(2)
      .collect {
        Log.e("3333", it.quote)
      }
    return Result.success()
  }
}