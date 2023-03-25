package com.dogandpigs.fitnote.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dogandpigs.fitnote.presentation.navigation.NavigationGraph
import com.dogandpigs.fitnote.presentation.ui.component.DefaultToast
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitNoteTheme {
                FitNoteApp(
                    mainViewModel
                )
            }
        }
    }
}

@Composable
private fun FitNoteApp(
    mainViewModel: MainViewModel,
) {
    val navController = rememberNavController()
    val eventStateFlow by mainViewModel.eventStateFlow.collectAsStateWithLifecycle()

    Scaffold {
        Box(Modifier.padding(it)) {
            NavigationGraph(
                navController,
                mainViewModel,
            )

            DefaultToast(
                mainEvent = eventStateFlow,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultMainActivity() {
    FitNoteTheme {
        FitNoteApp(
            mainViewModel = MainViewModel()
        )
    }
}
