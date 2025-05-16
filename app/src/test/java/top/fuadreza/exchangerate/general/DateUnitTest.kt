package top.fuadreza.exchangerate.general

import org.junit.Assert.assertEquals
import org.junit.Test
import top.fuadreza.tukaruang.core.helpers.DateHelper
import java.time.LocalDate


class DateUnitTest {
  @Test
  fun date_format_isCorrect() {
    val dateString = "2025-05-13 00:00:00+00"
    val croppedDate = dateString.substring(0, 10)

    val specificDate: LocalDate = LocalDate.of(2025, 5, 13)
    val expectedDate = DateHelper().dateToSimpleDate(specificDate)
    assertEquals(croppedDate, expectedDate)
  }
}