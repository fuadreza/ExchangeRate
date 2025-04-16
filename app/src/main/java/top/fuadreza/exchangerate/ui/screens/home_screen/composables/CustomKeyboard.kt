package top.fuadreza.exchangerate.ui.screens.home_screen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun CustomKeyboard() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .padding(
        vertical = 12.dp
      ),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(
          horizontal = 16.dp
        ),
    ) {
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey("1")
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey("2")
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey("3")
      }
    }
    Spacer(
      modifier = Modifier.height(height = Dp(value = 8f))
    )
    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(
          horizontal = 16.dp
        ),
    ) {
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey("4")
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey("5")
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey("6")
      }
    }
    Spacer(
      modifier = Modifier.height(height = Dp(value = 8f))
    )
    Row(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier
        .fillMaxWidth()
        .padding(
          horizontal = 16.dp
        ),
    ) {
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey("7")
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey("8")
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey("9")
      }
    }
  }
}