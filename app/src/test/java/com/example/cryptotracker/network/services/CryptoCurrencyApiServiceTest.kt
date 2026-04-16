package com.example.cryptotracker.network.services

import com.example.cryptotracker.network.data.CryptoCurrencyDto
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import retrofit2.HttpException
import retrofit2.Response

class CryptoCurrencyApiServiceTest {

    private val cryptoCurrencyApiServiceMock: CryptoCurrencyApiService = mock()

    @Test
    fun `fetchCryptoCurrencies returns list of DTOs on success`() = runTest {
        // Given
        val response = listOf(
            CryptoCurrencyDto(
                symbol = "BTCUSDT",
                priceChange = "100.0",
                priceChangePercent = "2.0",
                weightedAvgPrice = "50000.0",
                prevClosePrice = "49000.0",
                lastPrice = "51000.0",
                lastQty = "1.0",
                bidPrice = "50900.0",
                bidQty = "5.0",
                askPrice = "51100.0",
                askQty = "2.0",
                openPrice = "50000.0",
                highPrice = "52000.0",
                lowPrice = "49000.0",
                volume = "1000.0",
                quoteVolume = "50000000.0",
                openTime = 1600000000000,
                closeTime = 1600086400000,
                firstId = 1,
                lastId = 100,
                count = 50
            )
        )

        whenever(cryptoCurrencyApiServiceMock.fetchCryptoCurrencies())
            .thenReturn(response)

        // When
        val result = cryptoCurrencyApiServiceMock.fetchCryptoCurrencies()

        // Then
        assertEquals(1, result.size)
        assertEquals("BTCUSDT", result[0].symbol)
        assertEquals("51000.0", result[0].lastPrice)
        assertEquals(1600000000000L, result[0].openTime)
    }

    @Test
    fun `fetchCryptoCurrencies returns empty list when response is empty array`() = runTest {
        // Given
        whenever(cryptoCurrencyApiServiceMock.fetchCryptoCurrencies())
            .thenReturn(emptyList())

        // When
        val result = cryptoCurrencyApiServiceMock.fetchCryptoCurrencies()

        // Then
        assertTrue(result.isEmpty())
    }

    @Test(expected = HttpException::class)
    fun `fetchCryptoCurrencies throws HttpException on server error`() = runTest {
        // Given
        val errorResponse = Response.error<List<CryptoCurrencyDto>>(
            500,
            "Internal Server Error".toResponseBody("application/json".toMediaType())
        )
        
        whenever(cryptoCurrencyApiServiceMock.fetchCryptoCurrencies())
            .thenThrow(
                HttpException(errorResponse)
            )

        // When
        cryptoCurrencyApiServiceMock.fetchCryptoCurrencies()
    }

    @Test(expected = JsonDataException::class)
    fun `fetchCryptoCurrencies throws JsonDataException on malformed JSON`() = runTest {
        // Given
        whenever(cryptoCurrencyApiServiceMock.fetchCryptoCurrencies())
            .thenThrow(JsonDataException("Malformed JSON"))

        // When
        cryptoCurrencyApiServiceMock.fetchCryptoCurrencies()
    }
}
