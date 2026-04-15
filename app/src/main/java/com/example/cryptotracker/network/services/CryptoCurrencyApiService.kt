package com.example.cryptotracker.network.services

import com.example.cryptotracker.network.data.CryptoCurrencyDto
import retrofit2.http.GET

interface CryptoCurrencyApiService {

    @GET(value = "v3/ticker/24hr")
    suspend fun fetchCryptoCurrencies(): List<CryptoCurrencyDto>
}