package com.dogandpigs.fitnote.presentation.join

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.CompleteButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTextField
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import java.util.regex.Pattern

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun JoinScreen(
    viewModel: JoinViewModel, popBackStack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Join(uiState = state, popBackStack = popBackStack)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Join(
//    viewModel: JoinViewModel,
    uiState: JoinUiState, popBackStack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }
    var checkPwd by remember { mutableStateOf("") }

    var isNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPwdError by remember { mutableStateOf(false) }
    var isCheckPwdError by remember { mutableStateOf(false) }

    var readyCompletion by remember { mutableStateOf(false) }

    FitNoteScaffold(
        topBarTitle = "가입하기",
        onClickTopBarNavigationIcon = popBackStack,
    ) { paddingValue ->
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValue)
                    .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /**
                 * 이름
                 */
                DefaultTextField(
                    isError = isNameError,
                    value = name,
                    onValueChange = { newText ->
                        isNameError = true
                        name = newText
                    },
                    labelText = stringResource(id = R.string.name),
                    placeholderText = stringResource(id = R.string.name),
                )
                /**
                 * 이메일
                 */
                DefaultTextField(
                    isError = isEmailError,
                    value = email,
                    onValueChange = { emailValue ->
                        email = emailValue
                        if (emailValue == "" || Patterns.EMAIL_ADDRESS.matcher(
                                emailValue
                            ).matches()
                        ) {
                            isEmailError = false
                            return@DefaultTextField
                        }
                        isEmailError = true
                    },
                    labelText = stringResource(id = R.string.email),
                    placeholderText = stringResource(id = R.string.placeholder_email),
                )
                /**
                 * 비밀번호
                 */
                DefaultTextField(
                    isError = isPwdError,
                    value = pwd,
                    onValueChange = { textValue ->
                        pwd = textValue
                        if (Pattern.matches("^[a-zA-Z0-9]*\$", pwd)
                            && pwd.length > 7
                            && pwd.length < 17
                        ) {
                            isPwdError = false
                            return@DefaultTextField
                        }
                        isPwdError = true
                    },
                    labelText = stringResource(id = R.string.password),
                    placeholderText = stringResource(id = R.string.password),
                    isPassword = true,
                )
                /**
                 * 비밀번호 확인
                 */
                DefaultTextField(
                    isError = isCheckPwdError,
                    value = checkPwd,
                    onValueChange = { textValue ->
                        checkPwd = textValue
                        if (Pattern.matches("^[a-zA-Z0-9]*\$", checkPwd)
                            && checkPwd.length > 7
                            && checkPwd.length < 17
                        ) {
                            isCheckPwdError = false
                            return@DefaultTextField
                        }
                        isCheckPwdError = true
                    },
                    labelText = stringResource(id = R.string.check_password),
                    placeholderText = stringResource(id = R.string.check_password),
                    isPassword = true,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = stringResource(id = R.string.terms_and_conditions),
                    color = GrayScaleMidGray3,
                    style = LocalFitNoteTypography.current.textSmall,
                )
                HeightSpacer(height = 99.dp)
            }

            if (readyCompletion) {
                CompleteButton(
                    text = stringResource(id = R.string.completion),
                    onClick = {},
                )
            } else {
                CompleteButton(
                    text = stringResource(id = R.string.completion),
                    onClick = {},
                )
            }
        }
    }
}

private val mockUiState = JoinUiState(
    title = "mock JoinUiState title"
)

@FigmaPreview
@Composable
private fun PreviewJoin() {
    FitNoteTheme {
        Join(
//            viewModel = hiltViewModel(),
            uiState = mockUiState
        ) {}
    }
}
