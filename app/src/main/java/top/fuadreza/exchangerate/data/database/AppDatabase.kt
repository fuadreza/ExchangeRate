package top.fuadreza.exchangerate.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import top.fuadreza.exchangerate.data.dao.ExchangeRateDao
import top.fuadreza.exchangerate.data.entities.CurrencyRateEntity
import top.fuadreza.exchangerate.data.entities.ExchangeRateEntity

@Database(
  entities = [ExchangeRateEntity::class, CurrencyRateEntity::class],
  version = 1
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun exchangeRateDao(): ExchangeRateDao
}