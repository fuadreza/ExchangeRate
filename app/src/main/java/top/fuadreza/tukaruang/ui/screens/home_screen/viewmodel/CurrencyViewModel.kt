package top.fuadreza.tukaruang.ui.screens.home_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import top.fuadreza.tukaruang.data.repository.CurrencyRepository
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
  private val repository: CurrencyRepository
) : ViewModel() {
  fun fetchRates() {
    viewModelScope.launch(Dispatchers.IO) {
      try {
//        repository.fetchAndSaveLatestRates()
      } catch (e: Exception) {
        // Handle error
      }
    }
  }
}