package com.sucho.data.remote

import com.sucho.domain.responemodels.WalterWhiteQuoteResponse
import retrofit2.Retrofit
import retrofit2.http.GET

interface WalterWhiteQuoteApiService {
  companion object {
    fun createRetrofitService(retrofit: Retrofit): WalterWhiteQuoteApiService {
      return retrofit.create(WalterWhiteQuoteApiService::class.java)
    }
  }

  @GET("/api/quote/random?author=Walter+White") suspend fun getWalterWhiteQuote(): List<WalterWhiteQuoteResponse>
}