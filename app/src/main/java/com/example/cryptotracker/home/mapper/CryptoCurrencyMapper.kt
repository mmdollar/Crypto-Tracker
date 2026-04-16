package com.example.cryptotracker.home.mapper

import com.example.cryptotracker.home.data.CryptoCurrencyUi
import com.example.cryptotracker.network.data.CryptoCurrencyDto
import javax.inject.Inject

class CryptoCurrencyMapper @Inject constructor() {

    fun mapDtoToUiModel(dto: CryptoCurrencyDto): CryptoCurrencyUi = CryptoCurrencyUi(
        symbol = dto.symbol,
        priceChange = dto.priceChange,
        priceChangePercent = dto.priceChangePercent,
        weightedAvgPrice = dto.weightedAvgPrice,
        prevClosePrice = dto.prevClosePrice,
        lastPrice = dto.lastPrice,
        lastQty = dto.lastQty,
        bidPrice = dto.bidPrice,
        bidQty = dto.bidQty,
        askPrice = dto.askPrice,
        askQty = dto.askQty,
        openPrice = dto.openPrice,
        highPrice = dto.highPrice,
        lowPrice = dto.lowPrice,
        volume = dto.volume,
        quoteVolume = dto.quoteVolume,
        openTime = dto.openTime,
        closeTime = dto.closeTime,
        firstId = dto.firstId,
        lastId = dto.lastId,
        count = dto.count
    )
}