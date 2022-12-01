package com.dogandpigs.fitnote.presentation.splash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.join.JoinScreen
import com.dogandpigs.fitnote.presentation.join.JoinViewModel
import com.dogandpigs.fitnote.presentation.login.LoginScreen
import com.dogandpigs.fitnote.presentation.login.LoginViewModel
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import kotlinx.coroutines.delay

@Composable
internal fun SplashScreen(
    viewModel: SplashViewModel,
    navigateToHome: () -> Unit,
    navigateToJoin: () -> Unit,
    navigateToLogin: () -> Unit
) {
    Box {
        Splash(
            uiState = viewModel.uiState,
            navigateToHome = navigateToHome,
            navigateToJoin,
            navigateToLogin
        )
    }
}

@Composable
private fun Splash(
    uiState: SplashUiState,
    navigateToHome: () -> Unit,
    navigateToJoin: () -> Unit,
    navigateToLogin: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navigateToJoin() },
            modifier = Modifier
                .width(300.dp)
                .padding(0.dp, 5.dp)
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            
            ) {
            Text(text = "가입하기")
        }
        Button(
            onClick = { navigateToLogin() },
            modifier = Modifier
                .width(300.dp)
                .padding(0.dp, 5.dp)
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            Text(text = "로그인")
        }
    }
}

private val mockUiState = SplashUiState(
    title = "mock SplashUiState title"
)

@Composable
internal fun JoinRoute(
    viewModel: JoinViewModel = hiltViewModel(),
    navigateToHome: () -> Unit
) {
    JoinScreen(
        viewModel = viewModel,
        navigateToHome = navigateToHome
    )
}

@Composable
internal fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit
) {
    LoginScreen(
        viewModel = viewModel,
        navigateToHome = navigateToHome
    )
}

@FigmaPreview
@Composable
private fun PreviewSplash() {
    FitNoteTheme {
        Splash(
            uiState = mockUiState,
            navigateToHome = {},
            navigateToJoin = {},
            navigateToLogin = {}
        )
    }
}
