package top.fuadreza.exchangerate.data.mapper

import top.fuadreza.exchangerate.data.entities.CurrencyRateEntity
import top.fuadreza.exchangerate.data.entities.ExchangeRateEntity
import top.fuadreza.exchangerate.data.model.ExchangeRateResponse

fun ExchangeRateResponse.toEntities(): Pair<ExchangeRateEntity, List<CurrencyRateEntity>> {
  val rateEntity = ExchangeRateEntity(date = this.date, base = this.base)
  val currencyRates = this.rates.map { (currencyStr, value) ->
    CurrencyRateEntity(exchangeRateId = 0L, currencyCode = currencyStr, rate = value)
  }
  return rateEntity to currencyRates
}