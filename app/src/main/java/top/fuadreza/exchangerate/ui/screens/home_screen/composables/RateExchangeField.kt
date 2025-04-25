package top.fuadreza.exchangerate.ui.screens.home_screen.composables

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.fuadreza.exchangerate.R

@Composable
fun RateExchangeField(
  stateRateFromTextField: String,
  stateRateToTextField: String,
  onFocus: (String?) -> Unit
) {
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
          RateFromTextField(
            state = stateRateFromTextField,
            onFocus
          )
          Box(
            modifier = Modifier
              .width(
                80.dp
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
              Text(
                text = "USD",
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
          RateToTextField(
            state = stateRateToTextField,
            onFocus = {}
          )
          Box(
            modifier = Modifier
              .width(
                80.dp
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
              Text(
                text = "IDR",
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