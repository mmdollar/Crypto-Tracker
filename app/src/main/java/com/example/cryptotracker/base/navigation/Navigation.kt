package com.example.cryptotracker.base.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Garage
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.example.cryptotracker.base.ui.generalerror.GeneralError
import com.example.cryptotracker.home.ui.HomeScreen
import com.example.cryptotracker.home.ui.HomeViewModel

@Composable
fun BottomNavigationBar(backStack: NavBackStack<NavKey>) {
    NavigationBar {
        NavigationBarItem(
            selected = backStack.last() is Screen.Home,
            onClick = {
                backStack.clear()
                backStack.add(Screen.Home)
            },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
            label = { Text(text = "Home") }
        )
    }
}

@Composable
fun NavigationDisplay(backStack: NavBackStack<NavKey>) {
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { screen ->
            when (screen) {
                Screen.Home -> NavEntry(key = screen) {
                    val viewModel: HomeViewModel = hiltViewModel()
                    HomeScreen(viewModel = viewModel)
                }

                Screen.Details -> NavEntry(key = screen) {
//                    val viewModel: PlacesViewModel = hiltViewModel()
//                    PlacesScreen(viewModel = viewModel)
                }

                else -> NavEntry(key = screen) {
                    GeneralError()
                }
            }
        }
    )
}