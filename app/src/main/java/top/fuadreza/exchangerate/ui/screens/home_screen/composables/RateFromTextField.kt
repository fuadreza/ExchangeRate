package top.fuadreza.exchangerate.ui.screens.home_screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp

@Composable
fun RateFromTextField() {
  var text by remember { mutableStateOf("") }
  TextField(
    value = text,
    placeholder = {
      Text(
        text = "0.00",
        fontSize = 28.sp,
        color = Color.Black.copy(alpha = 0.7f)
      )
    },
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    textStyle = TextStyle(
      fontSize = 28.sp
    ),
    modifier = Modifier
      .background(
        color = Color.Transparent
      ),
    colors = TextFieldDefaults.colors(
      focusedContainerColor = Color.Transparent,
      unfocusedContainerColor = Color.Transparent,
      disabledContainerColor = Color.Transparent,
      errorContainerColor = Color.Transparent,
      focusedIndicatorColor = Color.Transparent,
      unfocusedIndicatorColor = Color.Transparent,
      disabledIndicatorColor = Color.Transparent,
      errorIndicatorColor = Color.Transparent,
      focusedTextColor = Color.Black,
      unfocusedTextColor = Color.Black,
      disabledTextColor = Color.Black,
    ),
    onValueChange = { value ->
      text = value
    }
  )
}