package top.fuadreza.tukaruang.ui.screens.home_screen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import top.fuadreza.tukaruang.core.helpers.ThousandSeparatorTransformation
import top.fuadreza.tukaruang.ui.composables.DisableSoftKeyboard

@Composable
fun RateFromTextField(state: String, onFocus: (String?) -> Unit) {
  val focusRequester = remember { FocusRequester() }
  DisableSoftKeyboard {
    TextField(
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
      visualTransformation = ThousandSeparatorTransformation(),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      textStyle = TextStyle(
        fontSize = 28.sp
      ),
      modifier = Modifier
        .background(
          color = Color.Transparent
        )
        .focusRequester(
          focusRequester
        )
        .onFocusEvent {
          if (it.isFocused) {
            onFocus("1")
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

  LaunchedEffect(Unit) {
    focusRequester.requestFocus()
  }
}