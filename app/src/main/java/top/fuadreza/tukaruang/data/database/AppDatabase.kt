package top.fuadreza.tukaruang.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import top.fuadreza.tukaruang.data.dao.ExchangeRateDao
import top.fuadreza.tukaruang.data.entities.CurrencyRateEntity
import top.fuadreza.tukaruang.data.entities.ExchangeRateEntity

@Database(
  entities = [ExchangeRateEntity::class, CurrencyRateEntity::class],
  version = 1
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun exchangeRateDao(): ExchangeRateDao
}