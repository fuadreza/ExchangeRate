package top.fuadreza.tukaruang.ui.screens.home_screen.composables

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import top.fuadreza.tukaruang.core.helpers.ThousandSeparatorTransformation
import top.fuadreza.tukaruang.ui.composables.DisableSoftKeyboard

@Composable
fun RateToTextField(state: String, onFocus: (String?) -> Unit) {
  DisableSoftKeyboard {
    TextField(
      enabled = false,
      value = TextFieldValue(
        text = state,
        selection = TextRange(
          state.length
        )
      ),
      placeholder = {
        Text(
          text = "0.00",
          fontSize = 28.sp,
          color = Color.Black.copy(alpha = 0.7f)
        )
      },
      textStyle = TextStyle(
        fontSize = 28.sp
      ),
      visualTransformation = ThousandSeparatorTransformation(),
      modifier = Modifier
        .background(
          color = Color.Transparent
        )
        .onFocusEvent {
          if (it.isFocused) {
            onFocus("2")
          } else {
            onFocus(null)
          }
        },
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
      onValueChange = { _ ->
//        text = value
      }
    )
  }
}