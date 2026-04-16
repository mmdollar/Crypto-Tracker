package com.example.cryptotracker.database.di

import android.content.Context
import androidx.room.Room
import com.example.cryptotracker.database.CryptoTrackerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "crypto_tracker_db"

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context = context,
        klass = CryptoTrackerDatabase::class.java,
        name = DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideCryptoCurrenciesDao(database: CryptoTrackerDatabase) = database.geCryptoCurrenciesDao()
}