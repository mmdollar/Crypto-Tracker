package com.example.cryptotracker.home.data

sealed interface HomeIntent {
    data object Refresh : HomeIntent
}