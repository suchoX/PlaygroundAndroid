package com.sucho.data.injection

import com.sucho.data.remote.KanyeQuoteApiService
import com.sucho.data.remote.KanyeQuoteApiService.Companion
import com.sucho.data.remote.RetrofitHelper
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
  @KanyeQuotesRetrofit
  fun provideKanyeQuoteRetrofit(
    okHttpClient: OkHttpClient
  ): Retrofit {
    return RetrofitHelper.createRetrofitClient(okHttpClient, Constants.KANYE_QUOTES_URL)
  }

  @Provides
  @Singleton
  fun provideKanyeQuoteApiService(@KanyeQuotesRetrofit retrofit: Retrofit): KanyeQuoteApiService {
    return KanyeQuoteApiService.createRetrofitService(retrofit)
  }
}