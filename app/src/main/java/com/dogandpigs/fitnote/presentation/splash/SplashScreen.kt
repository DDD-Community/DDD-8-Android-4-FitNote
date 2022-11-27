package com.dogandpigs.fitnote.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import kotlinx.coroutines.delay

@Composable
internal fun SplashScreen(
    viewModel: SplashViewModel,
    navigateToHome: () -> Unit,
) {
    Box {
        Splash(
            uiState = viewModel.uiState,
            navigateToHome = navigateToHome,
        )
    }
}

@Composable
private fun Splash(
    uiState: SplashUiState,
    navigateToHome: () -> Unit,
) {
    var count by rememberSaveable { mutableStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(text = count.toString())
    }

    LaunchedEffect(Unit) {
        repeat(3) {
            delay(1_000L)
            count = it + 1
        }
        navigateToHome()
    }
}

private val mockUiState = SplashUiState(
    title = "mock SplashUiState title"
)

@FigmaPreview
@Composable
private fun PreviewSplash() {
    FitNoteTheme {
        Splash(
            uiState = mockUiState,
            navigateToHome = {},
        )
    }
}
