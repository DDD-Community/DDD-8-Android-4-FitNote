package com.dogandpigs.fitnote.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.login.LoginScreen
import com.dogandpigs.fitnote.presentation.login.LoginViewModel
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme

@Composable
internal fun SplashScreen(
    viewModel: SplashViewModel,
    navigateToHome: () -> Unit,
    navigateToJoin: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToLesson: () -> Unit,
    navigateToMemberList: () -> Unit,
    navigateToMemberLesson: () -> Unit,
) {
    Box {
        Splash(
            uiState = viewModel.uiState,
            navigateToHome = navigateToHome,
            navigateToJoin,
            navigateToLogin,
            navigateToLesson,
            navigateToMemberList,
            navigateToMemberLesson,
        )
    }
}

@Composable
private fun Splash(
    uiState: SplashUiState,
    navigateToHome: () -> Unit,
    navigateToJoin: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToLesson: () -> Unit,
    navigateToMemberList: () -> Unit,
    navigateToMemberLesson: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_fitnote),
            contentDescription = null
        )
        HeightSpacer(height = 24.dp)
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
        Button(
            onClick = navigateToLesson,
            modifier = Modifier
                .width(300.dp)
                .padding(0.dp, 5.dp)
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrandPrimary,
                contentColor = Color.White,
            ),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            Text(text = "수업 목록")
        }
        Button(
            onClick = navigateToMemberList,
            modifier = Modifier
                .width(300.dp)
                .padding(0.dp, 5.dp)
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrandPrimary,
                contentColor = Color.White,
            ),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            Text(text = "회원 목록")
        }
        Button(
            onClick = navigateToMemberLesson,
            modifier = Modifier
                .width(300.dp)
                .padding(0.dp, 5.dp)
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrandPrimary,
                contentColor = Color.White,
            ),
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) {
            Text(text = "수업")
        }
    }
}

private val mockUiState = SplashUiState(
    title = "mock SplashUiState title"
)

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
            navigateToLogin = {},
            navigateToLesson = {},
            navigateToMemberList = {},
            navigateToMemberLesson = {},
        )
    }
}
