package com.example.cryptotracker.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.base.ui.data.UiState
import com.example.cryptotracker.home.data.CryptoCurrencyUi
import com.example.cryptotracker.home.repository.CryptoCurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val cryptoCurrencyRepository: CryptoCurrencyRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<List<CryptoCurrencyUi>>>(value = UiState.Loading)
    val uiState: StateFlow<UiState<List<CryptoCurrencyUi>>> = _uiState.asStateFlow()

    init {
        fetchCryptoCurrencies()
    }

    private fun fetchCryptoCurrencies() {
        cryptoCurrencyRepository.fetchCryptoCurrencies()
            .onStart { _uiState.value = UiState.Loading }
            .onEach { currency ->
                _uiState.value = UiState.Success(data = currency)
            }
            .catch { throwable ->
                _uiState.value = UiState.Error(message = throwable.message.orEmpty())
            }
            .launchIn(viewModelScope)
    }
}