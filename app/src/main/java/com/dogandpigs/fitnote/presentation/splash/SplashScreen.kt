package com.dogandpigs.fitnote.presentation.splash

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme

@Composable
internal fun SplashScreen(
    viewModel: SplashViewModel
) {
    Splash(viewModel.uiState)
}

@Composable
private fun Splash(
    uiState: SplashUiState
) {
    Text(text = uiState.title)
}

private val mockUiState = SplashUiState(
    title = "mock SplashUiState title"
)

@FigmaPreview
@Composable
private fun PreviewSplash() {
    FitNoteTheme {
        Splash(mockUiState)
    }
}
