package com.dogandpigs.fitnote.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.DebugMenu
import com.dogandpigs.fitnote.presentation.ui.component.DefaultNegativeButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultPositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
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
            navigateToJoin = {
                viewModel.cancelCheckLogin()
                navigateToJoin()
            },
            navigateToLogin = {
                viewModel.cancelCheckLogin()
                navigateToLogin()
            },
            onClickLogo = {
                isShowDebugMenu = !isShowDebugMenu
                viewModel.cancelCheckLogin()
            }
        )
        if (isShowDebugMenu) {
            DebugMenu(
                "홈" to navigateToHome,
                "가입하기" to navigateToJoin,
                "로그인" to navigateToLogin,
                "수업 목록" to navigateToLesson,
                "회원 목록" to navigateToMemberList,
                "수업" to navigateToMemberLesson,
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
            onClickLogo = onClickLogo,
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
    onClickLogo: () -> Unit = {},
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
        LogoImage(
            onClickImage = onClickLogo
        )
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
