package top.fuadreza.tukaruang.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateResponse(
  val date: String,
  val base: String,
  val rates: Map<String, Double>
)
