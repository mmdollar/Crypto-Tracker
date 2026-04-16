package com.example.cryptotracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptotracker.database.dao.CryptoCurrencyDao
import com.example.cryptotracker.database.entity.CryptoCurrencyEntity

@Database(
    entities = [CryptoCurrencyEntity::class],
    version = 2,
    exportSchema = false
)

abstract class CryptoTrackerDatabase : RoomDatabase() {

    abstract fun geCryptoCurrenciesDao(): CryptoCurrencyDao
}