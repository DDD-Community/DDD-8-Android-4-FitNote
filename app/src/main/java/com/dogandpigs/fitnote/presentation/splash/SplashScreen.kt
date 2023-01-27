package com.dogandpigs.fitnote.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.login.LoginScreen
import com.dogandpigs.fitnote.presentation.login.LoginViewModel
import com.dogandpigs.fitnote.presentation.ui.component.DefaultNegativeButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultPositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

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
    var isShowDebugMenu by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(viewModel.uiState.isLogin) {
        if (viewModel.uiState.isLogin) {
            navigateToHome()
        }
    }

    Box {
        Splash(
            uiState = viewModel.uiState,
            navigateToJoin = navigateToJoin,
            navigateToLogin = navigateToLogin,
            onClickLogo = {
                isShowDebugMenu = !isShowDebugMenu
            }
        )
        if (isShowDebugMenu) {
            DebugMenu(
                navigateToHome = navigateToHome,
                navigateToJoin = navigateToJoin,
                navigateToLogin = navigateToLogin,
                navigateToLesson = navigateToLesson,
                navigateToMemberList = navigateToMemberList,
                navigateToMemberLesson = navigateToMemberLesson,
            )
        }
    }
}

@Composable
private fun Splash(
    uiState: SplashUiState,
    navigateToJoin: () -> Unit,
    navigateToLogin: () -> Unit,
    onClickLogo: () -> Unit,
) {
    if (uiState.isShowJoinOrLogin) {
        JoinOrLogin(
            navigateToJoin = navigateToJoin,
            navigateToLogin = navigateToLogin,
        )
    } else {
        Logo(
            onClickLogo = onClickLogo
        )
    }
}

@Composable
private fun Logo(
    onClickLogo: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoMessage()
            HeightSpacer(height = 20.dp)
        }
        LogoImage(
            onClickImage = onClickLogo,
        )
        Row(modifier = Modifier.weight(1f)) {}
    }
}

@Composable
private fun LogoMessage() {
    Text(
        text = stringResource(id = R.string.fitnote_identity),
        color = GrayScaleMidGray3,
        style = LocalFitNoteTypography.current.titleDefault,
    )
}

@Composable
private fun LogoImage(
    onClickImage: () -> Unit = { },
) {
    Image(painter = painterResource(id = R.drawable.image_fitnote),
        contentDescription = null,
        modifier = Modifier.clickable {
            onClickImage()
        })
}

@FigmaPreview
@Composable
private fun JoinOrLogin(
    navigateToJoin: () -> Unit = {},
    navigateToLogin: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeightSpacer(height = 80.dp)
        LogoMessage()
        HeightSpacer(height = 16.dp)
        LogoImage()
        HeightSpacer(height = 42.dp)
        Image(
            painter = painterResource(id = R.drawable.image_fitnote_splash),
            contentDescription = null,
        )
        HeightSpacer(height = 205.dp)
        DefaultPositiveButton(
            reverseModifier = {
                Modifier
                    .padding(
                        horizontal = 16.dp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight()
            },
            positiveText = stringResource(id = R.string.btn_join),
            buttonTextStyle = LocalFitNoteTypography.current.buttonDefault,
            onClickPositive = navigateToJoin,
        )
        HeightSpacer(height = 24.dp)
        DefaultNegativeButton(
            reverseModifier = {
                Modifier
                    .padding(
                        horizontal = 16.dp
                    )
                    .fillMaxWidth()
                    .wrapContentHeight()
            },
            negativeText = stringResource(id = R.string.btn_login),
            buttonTextStyle = LocalFitNoteTypography.current.buttonDefault,
            onClickNegative = navigateToLogin,
        )
    }
}

@Composable
internal fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(), navigateToHome: () -> Unit
) {
    LoginScreen(
        viewModel = viewModel, navigateToHome = navigateToHome
    )
}

@Composable
private fun DebugMenu(
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
            .background(color = Color.Transparent),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navigateToJoin() },
            modifier = Modifier
                .width(300.dp)
                .padding(0.dp, 5.dp)
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black, contentColor = Color.White
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
                containerColor = Color.White, contentColor = Color.Black
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
        HeightSpacer(height = 24.dp)
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
            navigateToJoin = {},
            navigateToLogin = {},
            onClickLogo = {},
        )
    }
}
