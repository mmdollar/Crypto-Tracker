package com.example.cryptotracker.home.repository

import com.example.cryptotracker.database.dao.CryptoCurrencyDao
import com.example.cryptotracker.database.entity.CryptoCurrencyEntity
import com.example.cryptotracker.home.data.CryptoCurrencyUi
import com.example.cryptotracker.home.mapper.CryptoCurrencyMapper
import com.example.cryptotracker.network.data.CryptoCurrencyDto
import com.example.cryptotracker.network.services.CryptoCurrencyApiService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CryptoCurrencyRepositoryTest {

    private val cryptoCurrencyApiServiceMock: CryptoCurrencyApiService = mock()
    private val cryptoCurrencyMapperMock: CryptoCurrencyMapper = mock()
    private val cryptoCurrencyDaoMock: CryptoCurrencyDao = mock()
    private val cryptoCurrencyRepository = CryptoCurrencyRepository(
        cryptoCurrencyApiService = cryptoCurrencyApiServiceMock,
        cryptoCurrencyMapper = cryptoCurrencyMapperMock,
        cryptoCurrencyDao = cryptoCurrencyDaoMock
    )

    @Test
    fun `fetchCryptoCurrencies calls api, saves to dao, and emits ui model`() = runTest {
        // Given
        val mockDto = createMockDto("BTC")
        val mockEntity = createMockEntity("BTC")
        val mockUi = createMockUi("BTC")

        whenever(cryptoCurrencyApiServiceMock.fetchCryptoCurrencies()).thenReturn(listOf(mockDto))
        whenever(cryptoCurrencyMapperMock.mapDtoToUiModel(mockDto)).thenReturn(mockUi)
        whenever(cryptoCurrencyMapperMock.mapDtoToEntity(mockDto)).thenReturn(mockEntity)

        // When
        val result = cryptoCurrencyRepository.fetchCryptoCurrencies().first()

        // Then
        verify(cryptoCurrencyApiServiceMock).fetchCryptoCurrencies()
        verify(cryptoCurrencyDaoMock).insertCryptoCurrencies(listOf(mockEntity))
        assertEquals(listOf(mockUi), result)
    }

    @Test(expected = Exception::class)
    fun `fetchCryptoCurrencies propagates error when api fails`() = runTest {
        // Given
        whenever(cryptoCurrencyApiServiceMock.fetchCryptoCurrencies()).thenThrow(RuntimeException("API Error"))

        // When
        cryptoCurrencyRepository.fetchCryptoCurrencies().first()
    }

    @Test
    fun `fetchCryptoCurrenciesFromDatabase collects from dao and emits ui model`() = runTest {
        // Given
        val mockEntity = createMockEntity("ETH")
        val mockUi = createMockUi("ETH")

        whenever(cryptoCurrencyDaoMock.getAllCryptoCurrencies()).thenReturn(flowOf(listOf(mockEntity)))
        whenever(cryptoCurrencyMapperMock.mapEntityToUiModel(mockEntity)).thenReturn(mockUi)

        // When
        val result = cryptoCurrencyRepository.fetchCryptoCurrenciesFromDatabase().first()

        // Then
        verify(cryptoCurrencyDaoMock).getAllCryptoCurrencies()
        assertEquals(listOf(mockUi), result)
    }

    @Test
    fun `fetchCryptoCurrenciesFromDatabase emits empty list when dao is empty`() = runTest {
        // Given
        whenever(cryptoCurrencyDaoMock.getAllCryptoCurrencies()).thenReturn(flowOf(emptyList()))

        // When
        val result = cryptoCurrencyRepository.fetchCryptoCurrenciesFromDatabase().first()

        // Then
        assertEquals(emptyList<CryptoCurrencyUi>(), result)
    }

    private fun createMockDto(symbol: String) = CryptoCurrencyDto(
        symbol = symbol,
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
        closeTime = 0L,
        firstId = 0L,
        lastId = 0L,
        count = 0
    )

    private fun createMockEntity(symbol: String) = CryptoCurrencyEntity(
        symbol = symbol,
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
        closeTime = 0L,
        firstId = 0L,
        lastId = 0L,
        count = 0
    )

    private fun createMockUi(symbol: String) = CryptoCurrencyUi(
        symbol = symbol,
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
        closeTime = 0L,
        firstId = 0L,
        lastId = 0L,
        count = 0
    )
}
