package top.fuadreza.exchangerate.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import top.fuadreza.exchangerate.BuildConfig
import top.fuadreza.exchangerate.data.model.ExchangeRateResponse

interface CurrencyApiService {
  @GET("/v2.0/rates/latest")
  suspend fun getLatestRates(
    @Query("apikey") apiKey: String = BuildConfig.API_KEY
  ): ExchangeRateResponse

  @GET("/v2.0/rates/latest")
  suspend fun getLatestRatesWithBase(
    @Query("apikey") apiKey: String = BuildConfig.API_KEY,
    @Query("base") base: String
  ): ExchangeRateResponse
}