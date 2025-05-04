package top.fuadreza.exchangerate.data.repository

import top.fuadreza.exchangerate.data.api.CurrencyApiService
import top.fuadreza.exchangerate.data.dao.ExchangeRateDao
import top.fuadreza.exchangerate.data.mapper.toEntities
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