package com.sucho.playground.workmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.sucho.data.usecase.GetKanyeQuoteWithImageUseCase
import com.sucho.data.usecase.GetSwansonQuoteUseCase
import com.sucho.data.usecase.GetWalterWhiteQuoteWithImageUseCase
import com.sucho.playground.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.take

@HiltWorker
class BackgroundQuotesWorker @AssistedInject constructor(
  @Assisted val appContext: Context,
  @Assisted val workerParameters: WorkerParameters,
  private val kanyeQuoteWithImageUseCase: GetKanyeQuoteWithImageUseCase,
  private val swansonQuoteUseCase: GetSwansonQuoteUseCase,
  private val walterWhiteQuoteWithImageUseCase: GetWalterWhiteQuoteWithImageUseCase
) : CoroutineWorker(appContext, workerParameters) {
  companion object {
    const val QUOTES_CHANNEL_ID: String = "com.sucho.playground.workmanager.QUOTES"
    const val QUOTES_PROGRESS_NOTIFICATION_ID: Int = 100
    const val QUOTES_FINISHED_NOTIFICATION_ID: Int = 101
    const val NUMBER_OF_JOKES = 2
    const val MAX_PROGRESS = NUMBER_OF_JOKES * 3
  }

  private val notificationManager =
    appContext.getSystemService(Context.NOTIFICATION_SERVICE) as
        NotificationManager

  @ExperimentalCoroutinesApi
  override suspend fun doWork(): Result {
    var count = 0
    if (VERSION.SDK_INT >= VERSION_CODES.O) {
      createNotificationChannel()
    }
    setForeground(createForegroundInfo(count))
    kanyeQuoteWithImageUseCase.perform().flatMapLatest {
      delay(1000)
      count++
      setForeground(createForegroundInfo(count))
      swansonQuoteUseCase.perform()
    }.flatMapLatest {
      delay(1000)
      count++
      setForeground(createForegroundInfo(count))
      walterWhiteQuoteWithImageUseCase.perform()
    }.take(NUMBER_OF_JOKES)
      .collect {
        delay(1000)
        count++
        if(count < MAX_PROGRESS)
          setForeground(createForegroundInfo(count))
        else
          showFinishedNotification()
      }
    return Result.success()
  }

  private fun createForegroundInfo(progress: Int): ForegroundInfo {
    return ForegroundInfo(QUOTES_PROGRESS_NOTIFICATION_ID, createProgressNotification(progress))
  }

  private fun showFinishedNotification() {
    notificationManager.notify(QUOTES_FINISHED_NOTIFICATION_ID, createFinishedNotification())
  }

  private fun createProgressNotification(progress: Int): Notification {
    val title = applicationContext.getString(R.string.quotes_notification_title)
    val cancel = applicationContext.getString(R.string.cancel)
    val contentText = "$progress/$MAX_PROGRESS Quotes Downloaded"

    val intent = WorkManager.getInstance(applicationContext)
      .createCancelPendingIntent(id)

    val builder = NotificationCompat.Builder(applicationContext, QUOTES_CHANNEL_ID)
      .setContentTitle(title)
      .setTicker(title)
      .setContentText(contentText)
      .setSmallIcon(R.drawable.ic_work_notification)
      .setOngoing(true)
      .addAction(android.R.drawable.ic_delete, cancel, intent)
      .setProgress(MAX_PROGRESS, progress, false)
      .setSilent(true)
    return builder.build()
  }

  private fun createFinishedNotification(): Notification {
    val title = applicationContext.getString(R.string.quotes_notification_title)
    val builder = NotificationCompat.Builder(applicationContext, QUOTES_CHANNEL_ID)
      .setContentTitle(title)
      .setContentText(appContext.getString(R.string.quotes_notification_downloaded))
      .setSmallIcon(R.drawable.ic_work_notification)
      .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    return builder.build()
  }

  @RequiresApi(Build.VERSION_CODES.O)
  private fun createNotificationChannel(
  ): NotificationChannel {
    return NotificationChannel(
      QUOTES_CHANNEL_ID, appContext.getString(
        R.string.quotes_notification_channel_name
      ), NotificationManager.IMPORTANCE_DEFAULT
    ).also { channel ->
      notificationManager.createNotificationChannel(channel)
    }
  }
}