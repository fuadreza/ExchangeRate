package top.fuadreza.tukaruang.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import top.fuadreza.tukaruang.BuildConfig
import top.fuadreza.tukaruang.data.model.ExchangeRateResponse

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