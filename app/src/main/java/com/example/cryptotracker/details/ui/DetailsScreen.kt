package com.example.cryptotracker.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cryptotracker.R
import com.example.cryptotracker.home.data.CryptoCurrencyUi

@Composable
fun DetailsScreen(cryptoCurrency: CryptoCurrencyUi) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            Text(text = stringResource(id = R.string.details_screen_symbol_text))
            Text(text = cryptoCurrency.symbol)

            Field(
                label = stringResource(id = R.string.details_screen_price_change_text),
                text = cryptoCurrency.priceChange
            )

            Field(
                label = stringResource(id = R.string.details_screen_price_change_percent_text),
                text = cryptoCurrency.priceChangePercent
            )

            Field(
                label = stringResource(id = R.string.details_screen_weighted_avg_price_text),
                text = cryptoCurrency.weightedAvgPrice
            )

            Field(
                label = stringResource(id = R.string.details_screen_prev_close_price_text),
                text = cryptoCurrency.prevClosePrice
            )

            Field(
                label = stringResource(id = R.string.details_screen_last_price_text),
                text = cryptoCurrency.lastPrice
            )

            Field(
                label = stringResource(id = R.string.details_screen_last_qty_text),
                text = cryptoCurrency.lastQty
            )

            Field(
                label = stringResource(id = R.string.details_screen_bid_price_text),
                text = cryptoCurrency.bidPrice
            )

            Field(
                label = stringResource(id = R.string.details_screen_bid_qty_text),
                text = cryptoCurrency.bidQty
            )

            Field(
                label = stringResource(id = R.string.details_screen_ask_price_text),
                text = cryptoCurrency.askPrice
            )

            Field(
                label = stringResource(id = R.string.details_screen_ask_qty_text),
                text = cryptoCurrency.askQty
            )

            Field(
                label = stringResource(id = R.string.details_screen_open_price_text),
                text = cryptoCurrency.openPrice
            )

            Field(
                label = stringResource(id = R.string.details_screen_high_price_text),
                text = cryptoCurrency.highPrice
            )

            Field(
                label = stringResource(id = R.string.details_screen_low_price_text),
                text = cryptoCurrency.lowPrice
            )

            Field(
                label = stringResource(id = R.string.details_screen_volume_text),
                text = cryptoCurrency.volume
            )

            Field(
                label = stringResource(id = R.string.details_screen_quote_volume_text),
                text = cryptoCurrency.quoteVolume
            )

            Field(
                label = stringResource(id = R.string.details_screen_open_time_text),
                text = cryptoCurrency.openTime.toString()
            )

            Field(
                label = stringResource(id = R.string.details_screen_close_time_text),
                text = cryptoCurrency.closeTime.toString()
            )

            Field(
                label = stringResource(id = R.string.details_screen_first_id_text),
                text = cryptoCurrency.firstId.toString()
            )

            Field(
                label = stringResource(id = R.string.details_screen_last_id_text),
                text = cryptoCurrency.lastId.toString()
            )

            Field(
                label = stringResource(id = R.string.details_screen_count_text),
                text = cryptoCurrency.count.toString()
            )
        }
    }
}

@Composable
private fun Field(label: String, text: String) {
    Text(
        modifier = Modifier.padding(top = 16.dp),
        text = label
    )
    Text(text = text)
}

@Preview(
    showBackground = true,
    name = "Cryptocurrency Details Screen"
)
@Composable
private fun DetailsScreenPreview() {
    val cryptoCurrency = CryptoCurrencyUi(
        symbol = "BTC",
        priceChange = "1.0",
        priceChangePercent = "1.0",
        weightedAvgPrice = "1.0",
        prevClosePrice = "1.0",
        lastPrice = "1.0",
        lastQty = "1.0",
        bidPrice = "1.0",
        bidQty = "1.0",
        askPrice = "1.0",
        askQty = "1.0",
        openPrice = "1.0",
        highPrice = "1.0",
        lowPrice = "1.0",
        volume = "1.0",
        quoteVolume = "1.0",
        openTime = 1L,
        closeTime = 1L,
        firstId = 1L,
        lastId = 1L,
        count = 1
    )
    MaterialTheme {
        DetailsScreen(cryptoCurrency = cryptoCurrency)
    }
}