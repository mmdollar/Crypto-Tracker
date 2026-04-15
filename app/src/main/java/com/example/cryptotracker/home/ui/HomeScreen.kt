package com.example.cryptotracker.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cryptotracker.base.ui.data.UiState
import com.example.cryptotracker.base.ui.generalerror.GeneralError
import com.example.cryptotracker.home.data.CryptoCurrencyUi
import com.example.cryptotracker.home.data.HomeIntent

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState: UiState<List<CryptoCurrencyUi>> by viewModel.uiState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

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
private fun PullToRefreshContainer(isRefreshing: Boolean, uiState: List<CryptoCurrencyUi>, onSubmitIntent: (HomeIntent) -> Unit) {
    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = { onSubmitIntent(HomeIntent.Refresh) },
        state = pullToRefreshState
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items = uiState, key = { coin -> coin.symbol }) { coin ->
                ListItem(
                    headlineContent = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = coin.symbol)
                            Text(text = "${coin.priceChangePercent}%")
                        }
                    },
                    supportingContent = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Bid/Ask:")
                            Text(text = "${coin.bidPrice}/${coin.askPrice}")
                        }
                    }
                )
            }
        }
    }
}