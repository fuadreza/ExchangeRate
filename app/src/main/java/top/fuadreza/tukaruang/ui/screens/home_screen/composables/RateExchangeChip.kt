package top.fuadreza.tukaruang.ui.screens.home_screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import top.fuadreza.tukaruang.ui.screens.home_screen.viewmodel.CurrencyViewModel

@Composable
fun RateExchangeChip(
  viewModel: CurrencyViewModel = hiltViewModel(),
) {
  val currencyRateFrom by viewModel.currencyRateFrom.collectAsStateWithLifecycle()
  val currencyRateTo by viewModel.currencyRateTo.collectAsStateWithLifecycle()
  val rateToState by viewModel.rateTo.collectAsStateWithLifecycle()

  Box(
    modifier = Modifier
      .clip(
        shape = RoundedCornerShape(30.dp)
      )
      .background(
        color = Color.Black
      )
      .padding(
        horizontal = 12.dp,
        vertical = 6.dp
      )
      .wrapContentSize()
  ) {
    Text(
      text = "1 $currencyRateFrom = $currencyRateTo $rateToState",
      fontSize = 14.sp,
      color = Color.White
    )
  }
}