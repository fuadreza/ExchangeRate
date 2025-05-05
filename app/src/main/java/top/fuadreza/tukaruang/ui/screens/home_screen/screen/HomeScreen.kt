package top.fuadreza.tukaruang.ui.screens.home_screen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import top.fuadreza.tukaruang.core.constants.CustomKeyboardAction
import top.fuadreza.tukaruang.core.extensions.round
import top.fuadreza.tukaruang.core.helpers.ExchangeRates.rates
import top.fuadreza.tukaruang.ui.screens.home_screen.composables.CustomKeyboard
import top.fuadreza.tukaruang.ui.screens.home_screen.composables.HomeHeader
import top.fuadreza.tukaruang.ui.screens.home_screen.composables.RateExchangeChip
import top.fuadreza.tukaruang.ui.screens.home_screen.composables.RateExchangeField
import top.fuadreza.tukaruang.ui.screens.home_screen.viewmodel.CurrencyViewModel

@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  viewModel: CurrencyViewModel = hiltViewModel()
) {
  var stateRateFromTextField by remember { mutableStateOf("") }
  var stateRateToTextField by remember { mutableStateOf("") }
  var stateCurrencyFrom by remember { mutableStateOf("USD") }
  var stateCurrencyTo by remember { mutableStateOf("IDR") }
  var focusedTextField by remember { mutableStateOf<String?>("1") }

  LaunchedEffect(Unit) {
    viewModel.fetchRates()
  }

  fun calculateRates() {
    if (stateRateFromTextField.isNotBlank()) {
      if (rates.containsKey(stateCurrencyFrom) && rates.containsKey(stateCurrencyTo)) {
        val rateFrom: Double = rates[stateCurrencyFrom] ?: 0.0
        val rateTo: Double = rates[stateCurrencyTo] ?: 0.0

        val amountInBase = stateRateFromTextField.toInt() / rateFrom // Convert to base (USD)
        stateRateToTextField = (amountInBase * rateTo).round(2).toString()
      }
    } else {
      stateRateToTextField = ""
    }
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(
        vertical = 12.dp
      ),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    HomeHeader()
    Spacer(
      modifier = Modifier.height(height = Dp(value = 16f))
    )
    RateExchangeChip()
    Spacer(
      modifier = Modifier.height(height = Dp(value = 16f))
    )
    RateExchangeField(
      stateRateFromTextField,
      stateRateToTextField,
      stateCurrencyFrom,
      stateCurrencyTo,
      onFocus = { value ->
        focusedTextField = value
      },
      onSwap = {
        val tempCurrencyFrom = stateCurrencyFrom
        stateCurrencyFrom = stateCurrencyTo
        stateCurrencyTo = tempCurrencyFrom

        if (stateRateFromTextField.isNotBlank() && stateRateToTextField.isNotBlank()) {
          val tempRateFrom = stateRateFromTextField
          stateRateFromTextField = (stateRateToTextField.toDouble().round(2).toInt()).toString()
          stateRateToTextField = (tempRateFrom.toDouble().round(2).toInt()).toString()

          // Calculate last
          calculateRates()
        }
      }
    )
    Spacer(
      modifier = Modifier.weight(1f)
    )
    CustomKeyboard(
      onClick = { value ->
        if (value == CustomKeyboardAction.BACKSPACE) {
          stateRateFromTextField = stateRateFromTextField.dropLast(1)
        } else {
          if (focusedTextField == "1") {
            stateRateFromTextField += value
          } else if (focusedTextField == "2") {
            stateRateToTextField += value
          }
        }

        // Calculate last
        calculateRates()
      }
    )
  }
}