package com.dogandpigs.fitnote.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.CompleteButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTextField
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTextFieldLabel
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTextFieldPlaceholder
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.passwordVisualTransformation
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    email: String? = null,
    popBackStack: () -> Unit,
    navigateToHome: () -> Unit
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    val loginFailMessage = stringResource(id = R.string.login_failed_message)
    val findPasswordMessage = stringResource(id = R.string.find_password_message)
    LaunchedEffect(Unit) {
        viewModel.eventSharedFlow.collect {
            when (it) {
                is LoginEvent.None -> {}
                is LoginEvent.LoginFail -> {
                    Toast.makeText(context, loginFailMessage, Toast.LENGTH_SHORT).show()
                }
                is LoginEvent.FindPassword -> {
                    Toast.makeText(context, findPasswordMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    LaunchedEffect(state.loginState) {
        when (state.loginState) {
            is LoginUiState.LoginState.Success -> {
                navigateToHome()
            }
            is LoginUiState.LoginState.Failed -> {}
            is LoginUiState.LoginState.Loading -> {}
        }
    }

    email?.also {
        viewModel.setState {
            copy(email = email)
        }
    }

    Login(
        state = state,
        popBackStack = popBackStack,
        onEmailValueChange = { textValue ->
            viewModel.setState { copy(email = textValue) }
        },
        onPasswordValueChange = { textValue ->
            viewModel.setState { copy(password = textValue) }
        },
        onClickLoginButton = {
            viewModel.login()
//            navigateToHome()
        },
        onFindPasswordClick = {
            // TODO 0.2.0 개발예정
            viewModel.showToastFindPassword()
        },
    )
}

@Composable
private fun Login(
    state: LoginUiState,
    popBackStack: () -> Unit,
    onEmailValueChange: (String) -> Unit,
    onPasswordValueChange: (String) -> Unit,
    onClickLoginButton: () -> Unit,
    onFindPasswordClick: () -> Unit,
) {
    FitNoteScaffold(
        topBarTitle = "로그인",
        onClickTopBarNavigationIcon = popBackStack,
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
                    label = {
                        DefaultTextFieldLabel(text = stringResource(id = R.string.email))
                    },
                    placeholder = {
                        DefaultTextFieldPlaceholder(text = stringResource(id = R.string.placeholder_email))
                    },
                )

                DefaultTextField(
                    value = state.password,
                    onValueChange = onPasswordValueChange,
                    label = {
                        DefaultTextFieldLabel(text = stringResource(id = R.string.password))
                    },
                    placeholder = {
                        DefaultTextFieldPlaceholder(text = stringResource(id = R.string.password))
                    },
                    visualTransformation = passwordVisualTransformation,
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

                    TextButton(
                        onClick = onFindPasswordClick,
                    ) {
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
            popBackStack = {},
            onEmailValueChange = {},
            onPasswordValueChange = {},
            onClickLoginButton = {},
            onFindPasswordClick = {},
        )
    }
}
