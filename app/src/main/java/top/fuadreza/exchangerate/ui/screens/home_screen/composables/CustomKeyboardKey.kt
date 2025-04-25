package top.fuadreza.exchangerate.ui.screens.home_screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomKeyboardKey(
  text: String,
  onClick: (value: String) -> Unit
) {
  Box(
    modifier = Modifier
      .clip(
        shape = RoundedCornerShape(16.dp)
      )
      .background(
        color = Color.White.copy(alpha = 0.5f)
      )
      .fillMaxWidth()
      .clickable {
        onClick(text)
      },
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = text,
      fontSize = 40.sp,
      color = Color.Black,
      modifier = Modifier.padding(
        vertical = 20.dp
      )
    )
  }
}