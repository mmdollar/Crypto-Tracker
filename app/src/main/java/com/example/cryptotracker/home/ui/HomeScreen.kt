package com.example.cryptotracker.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptotracker.R
import com.example.cryptotracker.base.ui.data.UiState
import com.example.cryptotracker.base.ui.generalerror.GeneralError
import com.example.cryptotracker.base.ui.theme.CryptoTrackerTheme
import com.example.cryptotracker.home.data.CryptoCurrencyUi
import com.example.cryptotracker.home.data.HomeIntent

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToDetails: (CryptoCurrencyUi) -> Unit
) {
    val uiState: UiState<List<CryptoCurrencyUi>> by viewModel.uiState.collectAsState()
    val isRefreshing: Boolean by viewModel.isRefreshing.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.selectedCryptoCurrency.collect { cryptoCurrency ->
            onNavigateToDetails(cryptoCurrency)
        }
    }

    when (val state = uiState) {
        is UiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is UiState.Error -> GeneralError()

        is UiState.Success -> {
            PullToRefreshContainer(
                isRefreshing = isRefreshing,
                uiState = state.data,
                onSubmitIntent = viewModel::onSubmitIntent
            )
        }
    }
}

@Composable
private fun PullToRefreshContainer(
    isRefreshing: Boolean,
    uiState: List<CryptoCurrencyUi>,
    onSubmitIntent: (HomeIntent) -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        modifier = Modifier.padding(horizontal = 8.dp),
        isRefreshing = isRefreshing,
        onRefresh = { onSubmitIntent(HomeIntent.Refresh) },
        state = pullToRefreshState
    ) {
        CryptoCurrencyList(uiState = uiState, onSubmitIntent = onSubmitIntent)
    }
}

@Composable
private fun CryptoCurrencyList(
    uiState: List<CryptoCurrencyUi>,
    onSubmitIntent: (HomeIntent) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        stickyHeader {
            ListItem(
                shadowElevation = 2.dp,
                headlineContent = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = stringResource(id = R.string.home_screen_sticky_header_symbol_text))
                        Text(text = stringResource(id = R.string.home_screen_sticky_header_percent_change_text))
                    }
                }
            )
        }

        items(
            items = uiState,
            key = { cryptoCurrency -> cryptoCurrency.symbol }) { cryptoCurrency ->
            ListItem(
                modifier = Modifier.clickable(
                    role = Role.Button,
                    onClick = { onSubmitIntent(HomeIntent.CryptoCurrencySelected(cryptoCurrency = cryptoCurrency)) }
                ),
                shadowElevation = 2.dp,
                headlineContent = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = cryptoCurrency.symbol)
                        Text(text = "${cryptoCurrency.priceChangePercent}%")
                    }
                },
                supportingContent = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = stringResource(id = R.string.home_screen_bid_ask_text))
                        Text(text = "${cryptoCurrency.bidPrice}/${cryptoCurrency.askPrice}")
                    }
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    name = "Home Screen"
)
@Composable
private fun HomeScreenPreview() {
    CryptoTrackerTheme {
        PullToRefreshContainer(
            isRefreshing = false,
            uiState = listOf(
                CryptoCurrencyUi(
                    symbol = "BTC",
                    priceChange = "0.0",
                    priceChangePercent = "0.0",
                    weightedAvgPrice = "0.0",
                    prevClosePrice = "0.0",
                    lastPrice = "0.0",
                    lastQty = "0.0",
                    bidPrice = "0.0",
                    bidQty = "0.0",
                    askPrice = "0.0",
                    askQty = "0.0",
                    openPrice = "0.0",
                    highPrice = "0.0",
                    lowPrice = "0.0",
                    volume = "0.0",
                    quoteVolume = "0.0",
                    openTime = 0L,
                    closeTime = 123123123,
                    firstId = 0L,
                    lastId = 0L,
                    count = 0
                )
            ),
            onSubmitIntent = {}
        )
    }
}