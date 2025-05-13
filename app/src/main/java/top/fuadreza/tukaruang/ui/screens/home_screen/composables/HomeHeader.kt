package top.fuadreza.tukaruang.ui.screens.home_screen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import top.fuadreza.tukaruang.ui.screens.home_screen.viewmodel.CurrencyViewModel

@Composable
fun HomeHeader(
  viewModel: CurrencyViewModel = hiltViewModel()
) {
  val exchangeRateState by viewModel.exchangeRateState.collectAsStateWithLifecycle()
  val refreshingRatesState by viewModel.refreshingRates.collectAsStateWithLifecycle()

  Row(
    horizontalArrangement = Arrangement.Center
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "Exchange Rate",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(
        modifier = Modifier.height(
          height = 10.dp
        )
      )
      Row {
        Text(
          text = "Last Update: ${exchangeRateState.date}",
          fontSize = 14.sp,
        )
        Spacer(
          modifier = Modifier.width(
            width = 4.dp
          )
        )
        if (!refreshingRatesState)
          Box(
            modifier = Modifier
              .width(width = 24.dp)
              .height(height = 24.dp)
              .clickable {
                viewModel.fetchRatesToLatest()
              }
          ) {
            Icon(
              Icons.Rounded.Refresh,
              contentDescription = "Refresh",
            )
          }
        else
          CircularProgressIndicator(
            modifier = Modifier
              .width(width = 24.dp)
              .height(height = 24.dp),
            strokeWidth = 3.dp
          )
      }
    }
  }
}