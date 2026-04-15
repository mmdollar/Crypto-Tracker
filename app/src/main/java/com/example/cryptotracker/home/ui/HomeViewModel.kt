package com.example.cryptotracker.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptotracker.base.ui.data.UiState
import com.example.cryptotracker.home.data.CryptoCurrencyUi
import com.example.cryptotracker.home.data.HomeIntent
import com.example.cryptotracker.home.repository.CryptoCurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
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

    private val _isRefreshing = MutableStateFlow(value = false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    init {
        fetchCryptoCurrencies()
    }

    fun onSubmitIntent(intent: HomeIntent): Unit = when (intent) {
        is HomeIntent.Refresh -> fetchCryptoCurrencies(isRefreshing = true)
    }

    private fun fetchCryptoCurrencies(isRefreshing: Boolean = false) {
        cryptoCurrencyRepository.fetchCryptoCurrencies()
            .onStart {
                if (isRefreshing.not()) {
                    _uiState.value = UiState.Loading
                }

                _isRefreshing.value = isRefreshing
            }
            .onEach { currency ->
                _uiState.value = UiState.Success(data = currency)
            }
            .catch { throwable ->
                _uiState.value = UiState.Error(message = throwable.message.orEmpty())
            }
            .onCompletion {
                delay(300)
                _isRefreshing.value = false
            }
            .launchIn(viewModelScope)
    }
}