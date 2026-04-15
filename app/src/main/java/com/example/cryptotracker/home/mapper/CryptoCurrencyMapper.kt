package com.example.cryptotracker.home.mapper

import com.example.cryptotracker.home.data.CryptoCurrencyUi
import com.example.cryptotracker.network.data.CryptoCurrencyDto
import javax.inject.Inject

class CryptoCurrencyMapper @Inject constructor() {

    fun mapDtoToUiModel(dto: CryptoCurrencyDto): CryptoCurrencyUi = CryptoCurrencyUi(
        symbol = dto.symbol,
        priceChange = dto.priceChange,
        priceChangePercent = dto.priceChangePercent
    )
}