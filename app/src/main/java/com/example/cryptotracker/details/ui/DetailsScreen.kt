package com.example.cryptotracker.details.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.cryptotracker.home.data.CryptoCurrencyUi

@Composable
fun DetailsScreen(cryptoCurrency: CryptoCurrencyUi) {
    Text(text = "Hello From Details Screen this is for ${cryptoCurrency.symbol}")
}

//@Preview
//@Composable
//fun HomeScreen() {
//    DetailsScreen("test")
//}