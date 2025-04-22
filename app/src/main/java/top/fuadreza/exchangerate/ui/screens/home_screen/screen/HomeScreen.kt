package top.fuadreza.exchangerate.ui.screens.home_screen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.fuadreza.exchangerate.ui.screens.home_screen.composables.CustomKeyboard
import top.fuadreza.exchangerate.ui.screens.home_screen.composables.HomeHeader
import top.fuadreza.exchangerate.ui.screens.home_screen.composables.RateExchangeChip
import top.fuadreza.exchangerate.ui.screens.home_screen.composables.RateExchangeField

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
  var stateRateFromTextField by remember { mutableStateOf<String>("") }
  var stateRateToTextField by remember { mutableStateOf<String>("") }
  var focusedTextField by remember { mutableStateOf<String?>(null) }
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
      onFocus = { value ->
        focusedTextField = value
      }
    )
    Spacer(
      modifier = Modifier.weight(1f)
    )
    CustomKeyboard(
      onClick = { value ->
        if (focusedTextField == "1") {
          stateRateFromTextField += value
        } else {
          stateRateToTextField += value
        }
      }
    )
  }
}