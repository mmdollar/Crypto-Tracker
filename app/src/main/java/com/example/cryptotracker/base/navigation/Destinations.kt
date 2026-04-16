package com.example.cryptotracker.base.navigation

import androidx.navigation3.runtime.NavKey
import com.example.cryptotracker.home.data.CryptoCurrencyUi
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen: NavKey {
    @Serializable data object Home : Screen()
    @Serializable data class Details(val cryptoCurrency: CryptoCurrencyUi) : Screen()

}