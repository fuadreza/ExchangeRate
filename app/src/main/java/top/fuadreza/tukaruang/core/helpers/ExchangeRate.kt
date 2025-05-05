package top.fuadreza.tukaruang.core.helpers

object ExchangeRates {
  // Base currency is USD
  val rates: Map<String, Double> = mapOf(
    "USD" to 1.0,
    "IDR" to 16837.0 // Let's assume 1 USD = 16,837 IDR
  )
}