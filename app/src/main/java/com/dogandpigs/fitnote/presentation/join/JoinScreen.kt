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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.CompleteButton
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
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
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pwd by remember { mutableStateOf(TextFieldValue("")) }
    var checkPwd by remember { mutableStateOf(TextFieldValue("")) }

    var isNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPwdError by remember { mutableStateOf(false) }
    var isCheckPwdError by remember { mutableStateOf(false) }

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
                LoginTextField(
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
                LoginTextField(
                    isError = isEmailError,
                    value = email,
                    onValueChange = { emailValue ->
                        email = emailValue
                        if (emailValue.text == "" || Patterns.EMAIL_ADDRESS.matcher(
                                emailValue.text
                            ).matches()
                        ) {
                            isEmailError = false
                            return@LoginTextField
                        }
                        isEmailError = true
                    },
                    labelText = stringResource(id = R.string.email),
                    placeholderText = stringResource(id = R.string.placeholder_email),
                )
                /**
                 * 비밀번호
                 */
                LoginTextField(
                    isError = isPwdError,
                    value = pwd,
                    onValueChange = { textValue ->
                        pwd = textValue
                        if (Pattern.matches("^[a-zA-Z0-9]*\$", pwd.text)
                            && pwd.text.length > 7
                            && pwd.text.length < 17
                        ) {
                            isPwdError = false
                            return@LoginTextField
                        }
                        isPwdError = true
                    },
                    labelText = stringResource(id = R.string.password),
                    placeholderText = stringResource(id = R.string.password),
                )
                /**
                 * 비밀번호 확인
                 */
                LoginTextField(
                    isError = isCheckPwdError,
                    value = checkPwd,
                    onValueChange = { textValue ->
                        checkPwd = textValue
                        if (Pattern.matches("^[a-zA-Z0-9]*\$", checkPwd.text)
                            && checkPwd.text.length > 7
                            && checkPwd.text.length < 17
                        ) {
                            isCheckPwdError = false
                            return@LoginTextField
                        }
                        isCheckPwdError = true
                    },
                    labelText = stringResource(id = R.string.check_password),
                    placeholderText = stringResource(id = R.string.check_password),
                )
            }

            CompleteButton(
                stringResource(
                    id = R.string.btn_next
                ),
                onClick = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginTextField(
    isError: Boolean = false,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    labelText: String,
    placeholderText: String,
) {
    TextField(
        isError = isError,
        label = {
            Text(text = labelText)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            errorIndicatorColor = Color.Red,
            focusedIndicatorColor = BrandPrimary,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 10.dp)
            .background(Color.Transparent),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholderText)
        },
    )
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
