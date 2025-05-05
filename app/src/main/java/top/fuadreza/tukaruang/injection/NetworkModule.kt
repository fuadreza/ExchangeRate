package top.fuadreza.tukaruang.injection

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import top.fuadreza.tukaruang.data.api.CurrencyApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
  private const val BASE_URL = "https://api.currencyfreaks.com"

  private val loggingOkHttpClient = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
  }

  private val clientOkHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingOkHttpClient)
    .build()

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(
        Json.asConverterFactory(
          "application/json; charset=UTF8".toMediaType()
        )
      )
      .client(clientOkHttpClient)
      .build()

  @Provides
  @Singleton
  fun provideCurrencyApiService(retrofit: Retrofit): CurrencyApiService =
    retrofit.create(CurrencyApiService::class.java)
}