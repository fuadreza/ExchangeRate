package top.fuadreza.tukaruang.core.extensions
import kotlin.math.round

fun Double.round(decimals: Int): Double {
  var multiplier = 1.0
  repeat(decimals) { multiplier *= 10 }
  return round(this * multiplier) / multiplier
}