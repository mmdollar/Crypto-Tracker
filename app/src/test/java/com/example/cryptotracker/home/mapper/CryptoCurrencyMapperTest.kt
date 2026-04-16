package com.example.cryptotracker.home.mapper

import com.example.cryptotracker.database.entity.CryptoCurrencyEntity
import com.example.cryptotracker.network.data.CryptoCurrencyDto
import org.junit.Assert.assertEquals
import org.junit.Test

class CryptoCurrencyMapperTest {

    private val mapper = CryptoCurrencyMapper()

    @Test
    fun `mapDtoToUiModel maps all fields correctly`() {
        // Given
        val dto = createMockDto("BTC")

        // When
        val result = mapper.mapDtoToUiModel(dto)

        // Then
        assertEquals(dto.symbol, result.symbol)
        assertEquals(dto.priceChange, result.priceChange)
        assertEquals(dto.priceChangePercent, result.priceChangePercent)
        assertEquals(dto.weightedAvgPrice, result.weightedAvgPrice)
        assertEquals(dto.prevClosePrice, result.prevClosePrice)
        assertEquals(dto.lastPrice, result.lastPrice)
        assertEquals(dto.lastQty, result.lastQty)
        assertEquals(dto.bidPrice, result.bidPrice)
        assertEquals(dto.bidQty, result.bidQty)
        assertEquals(dto.askPrice, result.askPrice)
        assertEquals(dto.askQty, result.askQty)
        assertEquals(dto.openPrice, result.openPrice)
        assertEquals(dto.highPrice, result.highPrice)
        assertEquals(dto.lowPrice, result.lowPrice)
        assertEquals(dto.volume, result.volume)
        assertEquals(dto.quoteVolume, result.quoteVolume)
        assertEquals(dto.openTime, result.openTime)
        assertEquals(dto.closeTime, result.closeTime)
        assertEquals(dto.firstId, result.firstId)
        assertEquals(dto.lastId, result.lastId)
        assertEquals(dto.count, result.count)
    }

    @Test
    fun `mapDtoToEntity maps all fields correctly`() {
        // Given
        val dto = createMockDto("ETH")

        // When
        val result = mapper.mapDtoToEntity(dto)

        // Then
        assertEquals(dto.symbol, result.symbol)
        assertEquals(dto.priceChange, result.priceChange)
        assertEquals(dto.priceChangePercent, result.priceChangePercent)
        assertEquals(dto.weightedAvgPrice, result.weightedAvgPrice)
        assertEquals(dto.prevClosePrice, result.prevClosePrice)
        assertEquals(dto.lastPrice, result.lastPrice)
        assertEquals(dto.lastQty, result.lastQty)
        assertEquals(dto.bidPrice, result.bidPrice)
        assertEquals(dto.bidQty, result.bidQty)
        assertEquals(dto.askPrice, result.askPrice)
        assertEquals(dto.askQty, result.askQty)
        assertEquals(dto.openPrice, result.openPrice)
        assertEquals(dto.highPrice, result.highPrice)
        assertEquals(dto.lowPrice, result.lowPrice)
        assertEquals(dto.volume, result.volume)
        assertEquals(dto.quoteVolume, result.quoteVolume)
        assertEquals(dto.openTime, result.openTime)
        assertEquals(dto.closeTime, result.closeTime)
        assertEquals(dto.firstId, result.firstId)
        assertEquals(dto.lastId, result.lastId)
        assertEquals(dto.count, result.count)
    }

    @Test
    fun `mapEntityToUiModel maps all fields correctly`() {
        // Given
        val entity = createMockEntity("ADA")

        // When
        val result = mapper.mapEntityToUiModel(entity)

        // Then
        assertEquals(entity.symbol, result.symbol)
        assertEquals(entity.priceChange, result.priceChange)
        assertEquals(entity.priceChangePercent, result.priceChangePercent)
        assertEquals(entity.weightedAvgPrice, result.weightedAvgPrice)
        assertEquals(entity.prevClosePrice, result.prevClosePrice)
        assertEquals(entity.lastPrice, result.lastPrice)
        assertEquals(entity.lastQty, result.lastQty)
        assertEquals(entity.bidPrice, result.bidPrice)
        assertEquals(entity.bidQty, result.bidQty)
        assertEquals(entity.askPrice, result.askPrice)
        assertEquals(entity.askQty, result.askQty)
        assertEquals(entity.openPrice, result.openPrice)
        assertEquals(entity.highPrice, result.highPrice)
        assertEquals(entity.lowPrice, result.lowPrice)
        assertEquals(entity.volume, result.volume)
        assertEquals(entity.quoteVolume, result.quoteVolume)
        assertEquals(entity.openTime, result.openTime)
        assertEquals(entity.closeTime, result.closeTime)
        assertEquals(entity.firstId, result.firstId)
        assertEquals(entity.lastId, result.lastId)
        assertEquals(entity.count, result.count)
    }

    private fun createMockDto(symbol: String) = CryptoCurrencyDto(
        symbol = symbol,
        priceChange = "1.0",
        priceChangePercent = "2.0",
        weightedAvgPrice = "3.0",
        prevClosePrice = "4.0",
        lastPrice = "5.0",
        lastQty = "6.0",
        bidPrice = "7.0",
        bidQty = "8.0",
        askPrice = "9.0",
        askQty = "10.0",
        openPrice = "11.0",
        highPrice = "12.0",
        lowPrice = "13.0",
        volume = "14.0",
        quoteVolume = "15.0",
        openTime = 1000L,
        closeTime = 2000L,
        firstId = 100L,
        lastId = 200L,
        count = 10
    )

    private fun createMockEntity(symbol: String) = CryptoCurrencyEntity(
        symbol = symbol,
        priceChange = "1.0",
        priceChangePercent = "2.0",
        weightedAvgPrice = "3.0",
        prevClosePrice = "4.0",
        lastPrice = "5.0",
        lastQty = "6.0",
        bidPrice = "7.0",
        bidQty = "8.0",
        askPrice = "9.0",
        askQty = "10.0",
        openPrice = "11.0",
        highPrice = "12.0",
        lowPrice = "13.0",
        volume = "14.0",
        quoteVolume = "15.0",
        openTime = 1000L,
        closeTime = 2000L,
        firstId = 100L,
        lastId = 200L,
        count = 10
    )
}
