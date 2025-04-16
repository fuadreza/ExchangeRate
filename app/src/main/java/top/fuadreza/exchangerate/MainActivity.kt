package top.fuadreza.exchangerate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.tooling.preview.Preview
import top.fuadreza.exchangerate.ui.screens.home_screen.screen.HomeScreen
import top.fuadreza.exchangerate.ui.theme.ExchangeRateTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val largeRadialGradient = object : ShaderBrush() {
      override fun createShader(size: Size): Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
          colors = listOf(Color(0xFFD3D7C9), Color(0xFFE3E6FF)),
          center = Offset(x = 1f, y = size.width / 2f),
          radius = biggerDimension / 2f,
          colorStops = listOf(0f, 0.95f)
        )
      }
    }

    setContent {
      ExchangeRateTheme {
        Scaffold(
          modifier = Modifier
            .fillMaxSize()
        ) { innerPadding ->
          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(
                largeRadialGradient
              )
          ) {
            HomeScreen(
              modifier = Modifier.padding(innerPadding)
            )
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ExchangeRatePreview() {
  ExchangeRateTheme {
    HomeScreen()
  }
}