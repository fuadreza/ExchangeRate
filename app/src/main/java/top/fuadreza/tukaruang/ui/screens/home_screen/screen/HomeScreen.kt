package top.fuadreza.tukaruang.ui.screens.home_screen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import top.fuadreza.tukaruang.core.constants.CustomKeyboardAction
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

  // View Model
  val stateRateFromTextField by viewModel.stateRateFromTextField.collectAsStateWithLifecycle()
  val stateRateToTextField by viewModel.stateRateToTextField.collectAsStateWithLifecycle()

  LaunchedEffect(Unit) {
    viewModel.fetchRates()
  }

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(
        vertical = 12.dp
      ),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    HomeHeader(
      viewModel
    )
    Spacer(
      modifier = Modifier.height(height = Dp(value = 16f))
    )
    RateExchangeChip(
      viewModel,
    )
    Spacer(
      modifier = Modifier.height(height = Dp(value = 16f))
    )
    RateExchangeField(
      viewModel,
      stateRateFromTextField,
      stateRateToTextField,
      onFocus = { _ ->
//        focusedTextField = value
      },
      onSwap = {
        viewModel.swapRate()
      },
      onChangeCurrencyFrom = { value ->
        viewModel.changeRateFrom(value)
      },
      onChangeCurrencyTo = { value ->
        viewModel.changeRateTo(value)
      }
    )
    Spacer(
      modifier = Modifier.weight(1f)
    )
    CustomKeyboard(
      onClick = { value ->
        if (value == CustomKeyboardAction.BACKSPACE) {
          viewModel.deleteTextFrom()
        } else {
          viewModel.addText(value)
        }

        // Calculate last
        viewModel.calculateExchangeRates()
      }
    )
  }
}