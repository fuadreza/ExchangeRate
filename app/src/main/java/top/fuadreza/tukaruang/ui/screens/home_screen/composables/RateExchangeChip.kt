package top.fuadreza.tukaruang.ui.screens.home_screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RateExchangeChip() {
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
      text = "1 USD =  16.750 IDR",
      fontSize = 14.sp,
      color = Color.White
    )
  }
}