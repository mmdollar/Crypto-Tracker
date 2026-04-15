package com.example.cryptotracker.base.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen: NavKey {
    @Serializable data object Home : Screen()
    @Serializable data object Details : Screen()
    @Serializable data object GeneralError: Screen()
}