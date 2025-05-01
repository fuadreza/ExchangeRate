package top.fuadreza.exchangerate.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rates")
data class ExchangeRateEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val date: String,
  val base: String
)