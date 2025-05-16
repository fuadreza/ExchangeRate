package top.fuadreza.tukaruang.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import top.fuadreza.tukaruang.core.helpers.DateHelper
import top.fuadreza.tukaruang.data.api.CurrencyApiService
import top.fuadreza.tukaruang.data.dao.ExchangeRateDao
import top.fuadreza.tukaruang.data.mapper.toEntities
import java.time.LocalDate
import javax.inject.Inject

class CurrencyRepository @Inject constructor(
  private val api: CurrencyApiService,
  private val dao: ExchangeRateDao
) {
  @RequiresApi(Build.VERSION_CODES.O)
  suspend fun fetchAndSaveLatestRates() {
    val latestRates = dao.getLatestExchangeRate()
    var fetchNewRates = false
    if (latestRates != null) {
      val croppedDate = latestRates.date.substring(0, 10)
      val dateNow: LocalDate = LocalDate.now()
      val expectedDate = DateHelper().dateToSimpleDate(dateNow)
      if (croppedDate != expectedDate) {
        fetchNewRates = true
      }
    } else {
      fetchNewRates = true
    }

    if (fetchNewRates) {
      val response = api.getLatestRates()
      val (exchangeEntity, currencyEntities) = response.toEntities()
      dao.insertExchangeRateWithCurrencies(exchangeEntity, currencyEntities)
    }
  }

  suspend fun fetchAndSaveRatesWithBase(base: String) {
    val response = api.getLatestRatesWithBase(base = base)
    val (exchangeEntity, currencyEntities) = response.toEntities()
    dao.insertExchangeRateWithCurrencies(exchangeEntity, currencyEntities)
  }

  suspend fun getLatestRatesFromDb() = dao.getLatestExchangeRate()

  suspend fun getExchangeRateByBase(base: String) = dao.getExchangeRateByBase(base)

  suspend fun getCurrencyRatesFor(rateId: Long) = dao.getCurrencyRatesFor(rateId)
}