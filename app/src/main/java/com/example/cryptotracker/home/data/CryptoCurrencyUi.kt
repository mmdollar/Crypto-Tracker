package com.example.cryptotracker.home.data

import kotlinx.serialization.Serializable

@Serializable
data class CryptoCurrencyUi(
    val symbol: String,
    val priceChange: String,
    val priceChangePercent: String,
    val bidPrice: String,
    val askPrice: String
)
