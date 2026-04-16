package com.example.cryptotracker.home.repository

import com.example.cryptotracker.database.dao.CryptoCurrencyDao
import com.example.cryptotracker.home.data.CryptoCurrencyUi
import com.example.cryptotracker.home.mapper.CryptoCurrencyMapper
import com.example.cryptotracker.network.services.CryptoCurrencyApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CryptoCurrencyRepository @Inject constructor(
    private val cryptoCurrencyApiService: CryptoCurrencyApiService,
    private val cryptoCurrencyMapper: CryptoCurrencyMapper,
    private val cryptoCurrencyDao: CryptoCurrencyDao
) {

    fun fetchCryptoCurrencies(): Flow<List<CryptoCurrencyUi>> = flow {
        val response = cryptoCurrencyApiService.fetchCryptoCurrencies()

        val cryptoCurrencyUiList = response.map { cryptoCurrencyMapper.mapDtoToUiModel(dto = it) }

        cryptoCurrencyDao.insertCryptoCurrencies(cryptocurrencies = response.map {
            cryptoCurrencyMapper.mapDtoToEntity(
                dto = it
            )
        })

        emit(value = cryptoCurrencyUiList)

    }.flowOn(context = Dispatchers.IO)

    fun fetchCryptoCurrenciesFromDatabase(): Flow<List<CryptoCurrencyUi>> = flow {
        cryptoCurrencyDao.getAllCryptoCurrencies().collect { cryptoCurrencyEntityList ->
            val cryptoCurrencyUiList = cryptoCurrencyEntityList.map {
                cryptoCurrencyMapper.mapEntityToUiModel(entity = it)
                }

            emit(value = cryptoCurrencyUiList)
        }

    }.flowOn(context = Dispatchers.IO)
}