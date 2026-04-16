package com.example.cryptotracker.base.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.example.cryptotracker.R
import com.example.cryptotracker.base.ui.generalerror.GeneralError
import com.example.cryptotracker.details.ui.DetailsScreen
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
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = stringResource(id = R.string.bottom_bar_home_button_text)
                )
            },
            label = { Text(text = stringResource(id = R.string.bottom_bar_home_button_text)) }
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
                    HomeScreen(
                        viewModel = viewModel,
                        onNavigateToDetails = { cryptoCurrency ->
                            backStack.add(Screen.Details(cryptoCurrency = cryptoCurrency))
                        })
                }

                is Screen.Details -> NavEntry(key = screen) {
                    DetailsScreen(cryptoCurrency = screen.cryptoCurrency)
                }

                else -> NavEntry(key = screen) {
                    GeneralError()
                }
            }
        }
    )
}