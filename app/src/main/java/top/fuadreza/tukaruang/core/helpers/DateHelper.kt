package top.fuadreza.tukaruang.core.helpers

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class DateHelper {
  @RequiresApi(Build.VERSION_CODES.O)
  fun dateToSimpleDate(dateString: String): String? {
    return try {
      val offsetDateTime = OffsetDateTime.parse(dateString)
      val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
      offsetDateTime.format(formatter)
    } catch (e: Exception) {
      // Handle parsing errors, e.g., log the error or return null
      println("Error parsing date string: ${e.message}")
      null
    }
  }

  @RequiresApi(Build.VERSION_CODES.O)
  fun dateToSimpleDate(localDate: LocalDate): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault())
    return localDate.format(formatter)
  }
}