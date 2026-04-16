package com.example.cryptotracker.home.repository

import com.example.cryptotracker.database.dao.CryptoCurrencyDao
import com.example.cryptotracker.home.data.CryptoCurrencyUi
import com.example.cryptotracker.home.mapper.CryptoCurrencyMapper
import com.example.cryptotracker.network.data.CryptoCurrencyDto
import com.example.cryptotracker.network.services.CryptoCurrencyApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CryptoCurrencyRepository @Inject constructor(
    private val cryptoCurrencyApiService: CryptoCurrencyApiService,
    private val cryptoCurrencyMapper: CryptoCurrencyMapper,
    private val cryptoCurrencyDao: CryptoCurrencyDao
) {

    fun fetchCryptoCurrencies(): Flow<List<CryptoCurrencyUi>> = flow {
        val response = cryptoCurrencyApiService.fetchCryptoCurrencies()
        val uiModels = response.map { cryptoCurrencyMapper.mapDtoToUiModel(dto = it) }

        cacheResponse(response)

        emit(value = uiModels)
    }.flowOn(context = Dispatchers.IO)

    fun fetchCryptoCurrenciesFromDatabase(): Flow<List<CryptoCurrencyUi>> =
        cryptoCurrencyDao.getAllCryptoCurrencies().map { entities ->
            entities.map { cryptoCurrencyMapper.mapEntityToUiModel(entity = it) }
        }.flowOn(Dispatchers.IO)

    private suspend fun cacheResponse(response: List<CryptoCurrencyDto>) {
        if (response.isNotEmpty()) {
            val entities = response.map { cryptoCurrencyMapper.mapDtoToEntity(dto = it) }
            cryptoCurrencyDao.insertCryptoCurrencies(cryptocurrencies = entities)
        }
    }
}