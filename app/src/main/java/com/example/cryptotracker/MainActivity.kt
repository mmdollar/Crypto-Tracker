package com.example.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.cryptotracker.base.navigation.BottomNavigationBar
import com.example.cryptotracker.base.navigation.NavigationDisplay
import com.example.cryptotracker.base.navigation.Screen
import com.example.cryptotracker.base.ui.theme.CryptoTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                val backStack: NavBackStack<NavKey> = rememberNavBackStack(Screen.Home)
                val currentScreen: NavKey = backStack.last()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar(currentScreen = currentScreen, backStack = backStack)
                    },
                    bottomBar = {
                        if (currentScreen is Screen.Home) {
                            BottomNavigationBar(backStack = backStack)
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(paddingValues = innerPadding)) {
                        NavigationDisplay(backStack = backStack)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(currentScreen: NavKey, backStack: NavBackStack<NavKey>) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = when (currentScreen) {
                    is Screen.Home -> stringResource(id = R.string.app_name)
                    is Screen.Details -> stringResource(
                        id = R.string.top_bar_details_screen_text,
                        formatArgs = arrayOf(currentScreen.cryptoCurrency.symbol)
                    )

                    else -> stringResource(id = R.string.error_screen)
                }
            )
        },
        navigationIcon = {
            if (backStack.size > 1) {
                IconButton(onClick = { backStack.removeLastOrNull() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.top_bar_back_button_text)
                    )
                }
            }
        }
    )
}