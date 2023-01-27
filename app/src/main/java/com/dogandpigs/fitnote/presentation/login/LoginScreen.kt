package com.dogandpigs.fitnote.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.CompleteButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTextField
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Login(
        state = state,
        onEmailValueChange = { textValue ->
            viewModel.setState { copy(email = textValue) }
        },
        onPasswordValueChange = { textValue ->
            viewModel.setState { copy(password = textValue) }
        },
        onClickLoginButton = {
            viewModel.login()
        },
        navigateToHome = navigateToHome,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Login(
    state: LoginUiState,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onClickLoginButton: () -> Unit,
    navigateToHome: () -> Unit
) {
    val navController = rememberNavController()

    FitNoteScaffold(
        topBarTitle = "로그인",
        onClickTopBarNavigationIcon = { navController.navigateUp() },
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DefaultTextField(
                    value = state.email,
                    onValueChange = onEmailValueChange,
                    labelText = stringResource(id = R.string.email),
                    placeholderText = stringResource(id = R.string.placeholder_email),
                )

                DefaultTextField(
                    value = state.password,
                    onValueChange = onPasswordValueChange,
                    labelText = stringResource(id = R.string.password),
                    placeholderText = stringResource(id = R.string.password),
                    isPassword = true,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(20.dp, 0.dp)
                        .background(color = Color.White),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DefaultText(
                        text = stringResource(id = R.string.text_forget_pwd),
                        color = GrayScaleMidGray2,
                        style = LocalFitNoteTypography.current.textSmall,
                    )

                    TextButton(onClick = {}) {
                        Text(
                            text = stringResource(id = R.string.btn_forget_pwd), color = Color.Blue
                        )
                    }
                }
            }
            CompleteButton(
                stringResource(id = R.string.btn_login),
                onClick = onClickLoginButton
            )
        }
    }
}

private val mockUiState = LoginUiState(
    title = "mock LoginUiState title"
)

@FigmaPreview
@Composable
private fun PreviewLogin() {
    FitNoteTheme {
        Login(
            state = mockUiState,
            onEmailValueChange = {},
            onPasswordValueChange = {},
            onClickLoginButton = {},
            navigateToHome = {},
        )
    }
}
