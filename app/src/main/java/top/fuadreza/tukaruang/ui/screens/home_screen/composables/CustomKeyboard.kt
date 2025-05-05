package top.fuadreza.tukaruang.ui.screens.home_screen.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.fuadreza.tukaruang.R
import top.fuadreza.tukaruang.core.constants.CustomKeyboardAction


@Composable
fun CustomKeyboard(onClick: (String) -> Unit) {
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
        CustomKeyboardKey(
          "1",
          onClick = onClick
        )
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey(
          "2",
          onClick = onClick
        )
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey(
          "3",
          onClick = onClick
        )
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
        CustomKeyboardKey(
          "4",
          onClick = onClick
        )
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey(
          "5",
          onClick = onClick
        )
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey(
          "6",
          onClick = onClick
        )
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
        CustomKeyboardKey(
          "7",
          onClick = onClick
        )
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey(
          "8",
          onClick = onClick
        )
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey(
          "9",
          onClick = onClick
        )
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
        CustomKeyboardKey(
          " ",
          onClick = {}
        )
      }
      Box(
        modifier = Modifier
          .weight(1f)
      ) {
        CustomKeyboardKey(
          "0",
          onClick = onClick
        )
      }
      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
          .weight(1f)
          .clip(
            shape = RoundedCornerShape(16.dp)
          )
          .clickable {
            onClick(CustomKeyboardAction.BACKSPACE)
          }
          .align(
            Alignment.CenterVertically
          )
          .padding(
            vertical = 20.dp
          )
      ) {
        Icon(
          painter = painterResource(id = R.drawable.backspace),
          contentDescription = stringResource(R.string.content_description_switch),
          modifier = Modifier
            .size(40.dp),
          tint = Color.Black,
        )
      }
    }
  }
}