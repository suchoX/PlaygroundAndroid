package com.sucho.data.remote

import com.sucho.domain.responemodels.KanyeQuoteResponse
import retrofit2.Retrofit
import retrofit2.http.GET

interface SwansonQuoteApiService {
  companion object {
    fun createRetrofitService(retrofit: Retrofit): SwansonQuoteApiService {
      return retrofit.create(SwansonQuoteApiService::class.java)
    }
  }

  @GET("/v2/quotes") suspend fun getSwansonQuote(): List<String>
}