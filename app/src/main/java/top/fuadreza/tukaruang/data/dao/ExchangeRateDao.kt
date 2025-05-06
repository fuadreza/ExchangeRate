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
    val rateId = insertExchangeRate(rate)
    val currencyRates = currencies.map { it.copy(exchangeRateId = rateId) }
    insertCurrencyRates(currencyRates)
  }

  @Query("SELECT * FROM exchange_rates ORDER BY id DESC LIMIT 1")
  suspend fun getLatestExchangeRate(): ExchangeRateEntity?

  @Query("SELECT * FROM currency_rates WHERE exchangeRateId = :rateId")
  suspend fun getCurrencyRatesFor(rateId: Long): List<CurrencyRateEntity>

  @Query("SELECT * FROM exchange_rates WHERE base = :base LIMIT 1")
  suspend fun getExchangeRateByBase(base: String): ExchangeRateEntity?
}
