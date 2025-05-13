package top.fuadreza.tukaruang.ui.screens.home_screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import top.fuadreza.tukaruang.data.entities.CurrencyRateEntity
import top.fuadreza.tukaruang.data.entities.ExchangeRateEntity
import top.fuadreza.tukaruang.data.repository.CurrencyRepository
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
  private val repository: CurrencyRepository
) : ViewModel() {

  private val _exchangeRateState = MutableStateFlow(
    ExchangeRateEntity(
      date = "",
      base = "USD"
    )
  )
  val exchangeRateState: StateFlow<ExchangeRateEntity> = _exchangeRateState.asStateFlow()

  private val _currencyRates = MutableStateFlow<MutableList<CurrencyRateEntity>>(mutableListOf())
  val currencyRates: StateFlow<MutableList<CurrencyRateEntity>> = _currencyRates.asStateFlow()

  private val _currencyRateTo = MutableStateFlow("IDR")
  val currencyRateTo: StateFlow<String> = _currencyRateTo.asStateFlow()

  private val _rateTo = MutableStateFlow(0.0)
  val rateTo: StateFlow<Double> = _rateTo.asStateFlow()

  private val _refreshingRates = MutableStateFlow(false)
  val refreshingRates: StateFlow<Boolean> = _refreshingRates.asStateFlow()

  /*
   * Fetch rates online and update locally
   */
  fun fetchRatesToLatest() {
    viewModelScope.launch(Dispatchers.IO) {
      _refreshingRates.value = true
      repository.fetchAndSaveLatestRates()
      _refreshingRates.value = false
      fetchRates()
    }
  }

  /*
   * Fetch to get all Rates
   */
  fun fetchRates() {
    viewModelScope.launch(Dispatchers.IO) {
      try {
        // Base Exchange Rate
        val exchangeRate: ExchangeRateEntity? = repository.getExchangeRateByBase("USD")
        if (exchangeRate != null) {
          Log.d("RATES", "USD ${exchangeRate.date}")
          _exchangeRateState.update { _ ->
            exchangeRate
          }

          // Update Currency Rates To
          val currencyRates: List<CurrencyRateEntity> = repository.getCurrencyRatesFor(exchangeRate.id)
          _currencyRates.update { _ ->
            currencyRates.toMutableList()
          }

          // Update Rate To
          changeRateTo(_currencyRateTo.value)
        }
      } catch (e: Exception) {
        // Handle error
      }
    }
  }

  /**
   *  Change Rate To
   */
  fun changeRateTo(base: String) {
    val rate = _currencyRates.value.find {
      it.currencyCode == base
    }
    if (rate != null) {
      _rateTo.update {
        rate.rate
      }
    }
  }
}