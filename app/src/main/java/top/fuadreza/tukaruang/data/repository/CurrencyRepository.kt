package top.fuadreza.tukaruang.data.repository

import top.fuadreza.tukaruang.data.api.CurrencyApiService
import top.fuadreza.tukaruang.data.dao.ExchangeRateDao
import top.fuadreza.tukaruang.data.mapper.toEntities
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
  private val api: CurrencyApiService,
  private val dao: ExchangeRateDao
) {
  suspend fun fetchAndSaveLatestRates() {
    val response = api.getLatestRates()
    val (exchangeEntity, currencyEntities) = response.toEntities()
    dao.insertExchangeRateWithCurrencies(exchangeEntity, currencyEntities)
  }

  suspend fun fetchRatesWithBase(base: String) {
    val response = api.getLatestRatesWithBase(base = base)
    val (exchangeEntity, currencyEntities) = response.toEntities()
    dao.insertExchangeRateWithCurrencies(exchangeEntity, currencyEntities)
  }

  suspend fun getLatestRatesFromDb() = dao.getLatestExchangeRate()
}