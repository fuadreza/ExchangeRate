package top.fuadreza.exchangerate.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
  tableName = "currency_rates",
  foreignKeys = [ForeignKey(
    entity = ExchangeRateEntity::class,
    parentColumns = ["id"],
    childColumns = ["exchangeRateId"],
    onDelete = ForeignKey.CASCADE
  )],
  indices = [Index("exchangeRateId")]
)
data class CurrencyRateEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val exchangeRateId: Long, // FK ke ExchangeRateEntity
  val currencyCode: String, // pakai string biar fleksibel
  val rate: Double
)