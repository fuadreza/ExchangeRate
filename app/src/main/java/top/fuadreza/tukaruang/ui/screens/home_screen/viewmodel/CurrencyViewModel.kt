package top.fuadreza.tukaruang.ui.screens.home_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import top.fuadreza.tukaruang.core.extensions.round
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

  private val _currencyRateFrom = MutableStateFlow("USD")
  val currencyRateFrom: StateFlow<String> = _currencyRateFrom.asStateFlow()

  private val _currencyRateTo = MutableStateFlow("IDR")
  val currencyRateTo: StateFlow<String> = _currencyRateTo.asStateFlow()

  private val _rateTo = MutableStateFlow(0.0)
  val rateTo: StateFlow<Double> = _rateTo.asStateFlow()

  private val _refreshingRates = MutableStateFlow(false)
  val refreshingRates: StateFlow<Boolean> = _refreshingRates.asStateFlow()

  // Text Field
  private val _stateRateFromTextField = MutableStateFlow("")
  val stateRateFromTextField: StateFlow<String> = _stateRateFromTextField.asStateFlow()
  private val _stateRateToTextField = MutableStateFlow("")
  val stateRateToTextField: StateFlow<String> = _stateRateToTextField.asStateFlow()

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
          _exchangeRateState.update { _ ->
            exchangeRate
          }

          // Get Country Currency Rates
          val validCountryCurrencyCodes = java.util.Currency.getAvailableCurrencies()
            .map { it.currencyCode }
            .toSet()

          // Get Currency Rates from Database
          val currencyRates: List<CurrencyRateEntity> = repository.getCurrencyRatesFor(exchangeRate.id)

          // Filter Currency Rates with Country Based Currency Rates
          val filteredCurrency: List<CurrencyRateEntity> = currencyRates.filter {
            it.currencyCode in validCountryCurrencyCodes
          }.sortedBy { a ->
            a.currencyCode
          }

          _currencyRates.update { _ ->
            filteredCurrency.toMutableList()
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
   *  Change Rate From
   */
  fun changeRateFrom(base: String) {
    _currencyRateFrom.update {
      base
    }
    recalculateRates()
  }

  /**
   *  Change Rate To
   */
  fun changeRateTo(base: String) {
    // Update Rate To
    _currencyRateTo.value = base
    recalculateRates()
  }

  private fun recalculateRates() {
    // Update Rates
    val rateTo = _currencyRates.value.find {
      it.currencyCode == _currencyRateTo.value
    }
    if (rateTo != null) {
      val rateFrom = _currencyRates.value.find {
        it.currencyCode == _currencyRateFrom.value
      }
      if (rateFrom != null) {
        _rateTo.update {
          // TODO(fuad): handle pecahan
          (rateTo.rate / rateFrom.rate)
        }
      } else {
        _rateTo.update {
          rateTo.rate
        }
      }
    }

    // Calculate and display to text
    calculateExchangeRates()
  }

  fun calculateExchangeRates() {
    if (_stateRateFromTextField.value.isNotBlank()) {
      val amountInBase = _stateRateFromTextField.value.toDouble()
      _stateRateToTextField.value = (amountInBase * _rateTo.value).round(2).toString()
    } else {
      _stateRateToTextField.value = ""
    }
  }

  /**
   *  Swap Rate and Text From and To
   */
  fun swapRate() {
    // Rate
    val tempFrom = _currencyRateFrom.value
    _currencyRateFrom.update {
      _currencyRateTo.value
    }
    _currencyRateTo.update {
      tempFrom
    }

    // Text field
    val tempFromText = _stateRateFromTextField.value
    _stateRateFromTextField.update {
      _stateRateToTextField.value
    }
    _stateRateToTextField.update {
      tempFromText
    }

    changeRateTo(tempFrom)
  }

  //#region TEXT FIELD

  fun addText(value: String) {
    _stateRateFromTextField.value += value
  }

  fun deleteTextFrom() {
    _stateRateFromTextField.update {
      _stateRateFromTextField.value.dropLast(1)
    }
  }

  //#endregion TEXT FIELD
}