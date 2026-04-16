package com.example.cryptotracker.home.ui

import com.example.cryptotracker.base.ui.data.UiState
import com.example.cryptotracker.base.utils.NetworkMonitor
import com.example.cryptotracker.home.data.CryptoCurrencyUi
import com.example.cryptotracker.home.data.HomeIntent
import com.example.cryptotracker.home.repository.CryptoCurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val repository: CryptoCurrencyRepository = mock()
    private val networkMonitor: NetworkMonitor = mock()
    private lateinit var viewModel: HomeViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init calls fetchCryptoCurrencies and updates uiState to Success`() = runTest {
        // Given
        val mockCurrencies = listOf(createMockCryptoCurrencyUi("BTC"))
        whenever(repository.fetchCryptoCurrencies())
            .thenReturn(flowOf(mockCurrencies))

        // When
        viewModel = HomeViewModel(repository, networkMonitor)

        // Then
        assertEquals(UiState.Success(mockCurrencies), viewModel.uiState.value)
    }

    @Test
    fun `fetchCryptoCurrencies updates uiState to Error on failure when internet is available`() =
        runTest {
            // Given
            val errorMessage = "Network Error"
            whenever(repository.fetchCryptoCurrencies()).thenReturn(flow {
                throw Exception(
                    errorMessage
                )
            })
            whenever(networkMonitor.isInternetAvailable()).thenReturn(true)

            // When
            viewModel = HomeViewModel(repository, networkMonitor)

            // Then
            val state = viewModel.uiState.value
            assertTrue(state is UiState.Error)
            assertEquals(errorMessage, (state as UiState.Error).message)
        }

    @Test
    fun `fetchCryptoCurrencies fallbacks to cache when offline and cache is not empty`() = runTest {
        // Given
        val mockCache = listOf(createMockCryptoCurrencyUi("ETH"))
        whenever(repository.fetchCryptoCurrencies())
            .thenReturn(flow { throw Exception("No Internet") })
        whenever(networkMonitor.isInternetAvailable())
            .thenReturn(false)
        whenever(repository.fetchCryptoCurrenciesFromDatabase())
            .thenReturn(flowOf(mockCache))

        // When
        viewModel = HomeViewModel(repository, networkMonitor)

        // Then
        assertEquals(UiState.Success(mockCache), viewModel.uiState.value)
    }

    @Test
    fun `fetchCryptoCurrencies updates to Error when offline and cache is empty`() = runTest {
        // Given
        whenever(repository.fetchCryptoCurrencies())
            .thenReturn(flow { throw Exception("No Internet") })
        whenever(networkMonitor.isInternetAvailable())
            .thenReturn(false)
        whenever(repository.fetchCryptoCurrenciesFromDatabase())
            .thenReturn(flowOf(emptyList()))

        // When
        viewModel = HomeViewModel(repository, networkMonitor)

        // Then
        val state = viewModel.uiState.value
        assertTrue(state is UiState.Error)
        assertEquals("No Internet", (state as UiState.Error).message)
    }

    @Test
    fun `onSubmitIntent Refresh triggers reload and completes refreshing state`() = runTest {
        // Given
        whenever(repository.fetchCryptoCurrencies()).thenReturn(flowOf(emptyList()))
        viewModel = HomeViewModel(repository, networkMonitor)

        // When
        viewModel.onSubmitIntent(HomeIntent.Refresh)

        // Then
        assertEquals(false, viewModel.isRefreshing.value)
    }

    @Test
    fun `onSubmitIntent CryptoCurrencySelected emits selected currency`() = runTest {
        // Given
        whenever(repository.fetchCryptoCurrencies()).thenReturn(flowOf(emptyList()))
        viewModel = HomeViewModel(repository, networkMonitor)

        val selectedCrypto = createMockCryptoCurrencyUi(symbol = "BTC")
        val results = mutableListOf<CryptoCurrencyUi>()

        // Start collecting in the background before submitting the intent
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.selectedCryptoCurrency.collect { results.add(it) }
        }

        // When
        viewModel.onSubmitIntent(HomeIntent.CryptoCurrencySelected(selectedCrypto))

        // Then
        assertEquals(selectedCrypto, results.first())
    }

    private fun createMockCryptoCurrencyUi(symbol: String) = CryptoCurrencyUi(
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
