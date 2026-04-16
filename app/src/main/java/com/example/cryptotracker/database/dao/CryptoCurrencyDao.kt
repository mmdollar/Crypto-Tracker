package com.example.cryptotracker.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cryptotracker.database.entity.CryptoCurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoCurrencyDao {

    @Query(value = "SELECT * FROM cryptocurrencies")
    fun getAllCryptoCurrencies() : Flow<List<CryptoCurrencyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptoCurrencies(cryptocurrencies: List<CryptoCurrencyEntity>)
}