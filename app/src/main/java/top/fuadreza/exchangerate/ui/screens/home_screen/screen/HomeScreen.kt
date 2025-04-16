package top.fuadreza.exchangerate.ui.screens.home_screen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
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
    RateExchangeField()
    Spacer(
      modifier = Modifier.weight(1f)
    )
    CustomKeyboard()
  }
}