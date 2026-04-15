package com.example.cryptotracker.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cryptotracker.base.ui.data.UiState
import com.example.cryptotracker.base.ui.generalerror.GeneralError
import com.example.cryptotracker.home.data.CryptoCurrencyUi

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState: UiState<List<CryptoCurrencyUi>> by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is UiState.Error -> GeneralError()
        is UiState.Success -> {
            LazyColumn {
                items(items = state.data, key = { coin -> coin.symbol }) { coin ->
                    Text(
                        text = "${coin.symbol} - ${coin.priceChangePercent}",
                        modifier = Modifier
                    )

//                    PlaceCard(
//                        place = place,
//                        onClick = {
//                            viewModel.onSubmitIntent(intent = PlacesIntent.SelectPlace(place = place))
//                        }
//                    )
                }
            }
        }
    }
}