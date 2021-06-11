package com.sucho.data.remote

import com.sucho.domain.responemodels.KanyeQuoteResponse
import retrofit2.Retrofit
import retrofit2.http.GET

interface KanyeQuoteApiService {
  companion object {
    fun createRetrofitService(retrofit: Retrofit): KanyeQuoteApiService {
      return retrofit.create(KanyeQuoteApiService::class.java)
    }
  }

  @GET(".") suspend fun getFiveRandomJokes(): KanyeQuoteResponse
}