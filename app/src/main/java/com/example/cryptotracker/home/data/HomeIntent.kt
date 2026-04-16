package com.example.cryptotracker.home.data

sealed interface HomeIntent {
    data object Refresh : HomeIntent
    data class CryptoCurrencySelected(val cryptoCurrency: CryptoCurrencyUi) : HomeIntent
}