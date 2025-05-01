package top.fuadreza.exchangerate.injection

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import top.fuadreza.exchangerate.data.dao.ExchangeRateDao
import top.fuadreza.exchangerate.data.database.AppDatabase

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
    return Room.databaseBuilder(
      context,
      AppDatabase::class.java,
      "exchange_rate_db"
    ).build()
  }

  @Provides
  fun provideExchangeRateDao(db: AppDatabase): ExchangeRateDao {
    return db.exchangeRateDao()
  }
}
