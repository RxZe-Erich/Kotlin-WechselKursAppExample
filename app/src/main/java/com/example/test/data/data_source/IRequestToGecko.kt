package com.example.test.data.data_source

import com.example.test.data.data_source.DTO.CoinsList
import com.example.test.data.data_source.DTO.MarketChart
import com.example.test.data.data_source.DTO.Markets
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface IRequestToGecko {
    @GET("coins/{id}/market_chart")
    suspend fun getHistoricalData(@Path("id")id: String,
                                  @Query("vs_currency")currencyType: String,
                                  @Query("days")days: Int,
                                  @Query("interval")interval: String,
                                  @Query("precision")precision: Int,
                                  @Query("x_cg_api_key")apiKey: String): MarketChart
    @GET("coins/markets")
    suspend fun getCoinInfo(@Query("vs_currency")currencyType: String,
                            @Query("precision")preciosion: Int,
                            @Query("page")page: Int,
                            @Query("per_page")per_page: Int,
                            @Query("x_cg_api_key") apiKey: String
                            ) : Markets
}