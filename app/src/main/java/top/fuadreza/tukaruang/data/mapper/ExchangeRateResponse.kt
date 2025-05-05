package top.fuadreza.tukaruang.data.mapper

import top.fuadreza.tukaruang.data.entities.CurrencyRateEntity
import top.fuadreza.tukaruang.data.entities.ExchangeRateEntity
import top.fuadreza.tukaruang.data.model.ExchangeRateResponse

fun ExchangeRateResponse.toEntities(): Pair<ExchangeRateEntity, List<CurrencyRateEntity>> {
  val rateEntity = ExchangeRateEntity(date = this.date, base = this.base)
  val currencyRates = this.rates.map { (currencyStr, value) ->
    CurrencyRateEntity(exchangeRateId = 0L, currencyCode = currencyStr, rate = value)
  }
  return rateEntity to currencyRates
}