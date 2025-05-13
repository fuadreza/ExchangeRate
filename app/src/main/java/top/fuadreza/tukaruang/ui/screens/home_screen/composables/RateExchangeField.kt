package top.fuadreza.tukaruang.ui.screens.home_screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import top.fuadreza.tukaruang.R
import top.fuadreza.tukaruang.ui.screens.home_screen.viewmodel.CurrencyViewModel

@Composable
fun RateExchangeField(
  viewModel: CurrencyViewModel = hiltViewModel(),
  stateRateFromTextField: String,
  stateRateToTextField: String,
  onFocus: (String?) -> Unit,
  onSwap: () -> Unit,
  onChangeCurrencyTo: (String) -> Unit,
  onChangeRateTo: (Double) -> Unit
) {
  var expanded by remember { mutableStateOf(false) }

  val currencyRatesState by viewModel.currencyRates.collectAsStateWithLifecycle()
  val currencyRateFromState by viewModel.currencyRateFrom.collectAsStateWithLifecycle()
  val currencyRateToState by viewModel.currencyRateTo.collectAsStateWithLifecycle()

  Box(
    contentAlignment = Alignment.Center,
  ) {
    Column(
      modifier = Modifier.padding(
        horizontal = 16.dp
      )
    ) {
      Box(
        modifier = Modifier
          .clip(
            shape = RoundedCornerShape(16.dp)
          )
          .background(
            color = Color.White.copy(alpha = 0.5f),
          )
          .padding(
            horizontal = 12.dp,
            vertical = 6.dp
          )
          .fillMaxWidth()
          .wrapContentHeight()
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier.weight(1f)
          ) {
            RateFromTextField(
              state = stateRateFromTextField,
              onFocus
            )
          }
          Box(
            modifier = Modifier
              .width(
                100.dp
              )
              .border(
                width = 0.4.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(
                  14.dp
                )
              )
              .clip(
                shape = RoundedCornerShape(14.dp)
              )
              .clickable {
                // TODO: Handle click drop down currency
              }
              .padding(
                horizontal = 10.dp,
                vertical = 8.dp
              )
              .wrapContentHeight()
          ) {
            Row {
              AsyncImage(
                model = "https://currencyfreaks.com/photos/flags/${currencyRateFromState.lowercase()}.webp",
                contentDescription = null,
              )
              Spacer(
                modifier = Modifier.width(
                  2.dp
                )
              )
              Text(
                text = currencyRateFromState,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.weight(
                  1.0f
                )
              )
              Icon(
                Icons.Rounded.ArrowDropDown,
                contentDescription = "Drop Down",
              )
            }
          }
        }
      }
      Spacer(
        modifier = Modifier.height(
          8.dp
        )
      )
      Box(
        modifier = Modifier
          .clip(
            shape = RoundedCornerShape(16.dp)
          )
          .background(
            color = Color.White.copy(alpha = 0.5f),
          )
          .padding(
            horizontal = 12.dp,
            vertical = 6.dp
          )
          .fillMaxWidth()
          .wrapContentHeight()
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier.weight(1f)
          ) {
            RateToTextField(
              state = stateRateToTextField,
              onFocus = {}
            )
          }
          Box(
            modifier = Modifier
              .width(
                100.dp
              )
              .border(
                width = 0.4.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(
                  14.dp
                )
              )
              .clip(
                shape = RoundedCornerShape(14.dp)
              )
              .clickable {
                expanded = true
              }
              .padding(
                horizontal = 10.dp,
                vertical = 8.dp
              )
              .wrapContentHeight()
          ) {
            Row {
              AsyncImage(
                model = "https://currencyfreaks.com/photos/flags/${currencyRateToState.lowercase()}.webp",
                contentDescription = null,
              )
              Spacer(
                modifier = Modifier.width(
                  2.dp
                )
              )
              Text(
                text = currencyRateToState,
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.weight(
                  1.0f
                )
              )
              Icon(
                Icons.Rounded.ArrowDropDown,
                contentDescription = "Drop Down",
              )
            }
            DropdownMenu(
              expanded = expanded,
              onDismissRequest = { expanded = false }
            ) {
              currencyRatesState.forEach{ currencyRates ->
                DropdownMenuItem(
                  text = {
                    Text(
                      currencyRates.currencyCode
                    )
                  },
                  onClick = {
                    onChangeCurrencyTo(currencyRates.currencyCode)
                    expanded = false
                  }
                )
              }
            }
          }
        }
      }
    }
    Box(
      modifier = Modifier
        .clip(
          shape = CircleShape
        )
        .background(
          color = Color.Black
        )
        .width(50.dp)
        .height(50.dp)
        .clickable {
          onSwap()
        }
    ) {
      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight()
      ) {
        Icon(
          painter = painterResource(id = R.drawable.switch_vertical),
          contentDescription = stringResource(R.string.content_description_switch),
          modifier = Modifier
            .size(24.dp),
          tint = Color.White,
        )
      }
    }
  }
}