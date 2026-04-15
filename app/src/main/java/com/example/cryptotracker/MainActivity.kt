package com.example.cryptotracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
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
                val backStack = rememberNavBackStack(Screen.Home)

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(backStack = backStack) }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(paddingValues = innerPadding)) {
                        NavigationDisplay(backStack = backStack)
                    }
                }
            }
        }
    }
}