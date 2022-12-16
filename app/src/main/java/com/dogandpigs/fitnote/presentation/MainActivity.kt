package com.dogandpigs.fitnote.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dogandpigs.fitnote.presentation.navigation.BottomNavigationBar
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes
import com.dogandpigs.fitnote.presentation.navigation.NavigationGraph
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitNoteTheme {
                FitNoteApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FitNoteApp() {
    val navController = rememberNavController()
    val screens = listOf(
        NavRoutes.Home, NavRoutes.FirstPage, NavRoutes.SecondPage, NavRoutes.ThirdPage
    )
    val showBottomBar =
        navController.currentBackStackEntryAsState().value?.destination?.route in screens.map {
            it.route
        }
    Scaffold(bottomBar = {
        if (showBottomBar) {
            BottomNavigationBar(
                navController = navController
            )
        }
    }) {
        Box(Modifier.padding(it)) {
            NavigationGraph(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultMainActivity() {
    FitNoteTheme {
        FitNoteApp()
    }
}
