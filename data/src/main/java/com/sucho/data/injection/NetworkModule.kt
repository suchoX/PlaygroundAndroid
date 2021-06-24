package com.sucho.data.injection

import com.sucho.data.remote.KanyeQuoteApiService
import com.sucho.data.remote.RetrofitHelper
import com.sucho.data.remote.SwansonQuoteApiService
import com.sucho.data.remote.WalterWhiteQuoteApiService
import com.sucho.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
  @Provides
  @Singleton
  fun provideHttpClient(): OkHttpClient {
    return RetrofitHelper.createOkHttpClient()
  }

  @Provides
  @Singleton
  @KanyeQuoteRetrofit
  fun provideKanyeQuoteRetrofit(
    okHttpClient: OkHttpClient
  ): Retrofit {
    return RetrofitHelper.createRetrofitClient(okHttpClient, Constants.KANYE_QUOTES_URL)
  }

  @Provides
  @Singleton
  fun provideKanyeQuoteApiService(@KanyeQuoteRetrofit retrofit: Retrofit): KanyeQuoteApiService {
    return KanyeQuoteApiService.createRetrofitService(retrofit)
  }

  @Provides
  @Singleton
  @SwansonQuoteRetrofit
  fun provideSwansonQuoteRetrofit(
    okHttpClient: OkHttpClient
  ): Retrofit {
    return RetrofitHelper.createRetrofitClient(okHttpClient, Constants.SWANSON_QUOTES_URL)
  }

  @Provides
  @Singleton
  fun provideSwansonQuoteApiService(@SwansonQuoteRetrofit retrofit: Retrofit): SwansonQuoteApiService {
    return SwansonQuoteApiService.createRetrofitService(retrofit)
  }

  @Provides
  @Singleton
  @WalterWhiteQuoteRetrofit
  fun provideWalterWhiteQuoteRetrofit(
    okHttpClient: OkHttpClient
  ): Retrofit {
    return RetrofitHelper.createRetrofitClient(okHttpClient, Constants.WALTER_WHITE_QUOTES_URL)
  }

  @Provides
  @Singleton
  fun provideWalterWhiteQuoteApiService(@WalterWhiteQuoteRetrofit retrofit: Retrofit): WalterWhiteQuoteApiService {
    return WalterWhiteQuoteApiService.createRetrofitService(retrofit)
  }
}