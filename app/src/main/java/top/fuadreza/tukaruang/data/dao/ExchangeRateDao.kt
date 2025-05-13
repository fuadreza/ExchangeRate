package top.fuadreza.tukaruang.data.dao

import androidx.room.*
import top.fuadreza.tukaruang.data.entities.CurrencyRateEntity
import top.fuadreza.tukaruang.data.entities.ExchangeRateEntity

@Dao
interface ExchangeRateDao {
  @Insert
  suspend fun insertExchangeRate(rate: ExchangeRateEntity): Long

  @Insert
  suspend fun insertCurrencyRates(rates: List<CurrencyRateEntity>)

  @Transaction
  suspend fun insertExchangeRateWithCurrencies(
    rate: ExchangeRateEntity,
    currencies: List<CurrencyRateEntity>
  ) {
    val exchangeRate = getExchangeRateByBase(base = rate.base)
    var rateId: Long = -1L
    if (exchangeRate != null) {
      rateId = exchangeRate.id
      updateExchangeRate(rate.copy(
        id = exchangeRate.id
      ))
    } else {
      rateId = insertExchangeRate(rate)
    }
    if (rateId != -1L) {
      val currencyRates = currencies.map { it.copy(exchangeRateId = rateId) }
      insertCurrencyRates(currencyRates)
    }
  }

  @Update
  suspend fun updateExchangeRate(rate: ExchangeRateEntity)

  @Query("SELECT * FROM exchange_rates ORDER BY id DESC LIMIT 1")
  suspend fun getLatestExchangeRate(): ExchangeRateEntity?

  @Query("SELECT * FROM currency_rates WHERE exchangeRateId = :rateId")
  suspend fun getCurrencyRatesFor(rateId: Long): List<CurrencyRateEntity>

  @Query("SELECT * FROM exchange_rates WHERE base = :base LIMIT 1")
  suspend fun getExchangeRateByBase(base: String): ExchangeRateEntity?
}
