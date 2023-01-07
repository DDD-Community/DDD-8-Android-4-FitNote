package com.dogandpigs.fitnote.presentation.join

import android.util.Log
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.CompleteButton
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import java.util.regex.Pattern

@Composable
internal fun JoinScreen(
    viewModel: JoinViewModel, popBackStack: () -> Unit
) {
    Join(
//        viewModel = viewModel,
        uiState = viewModel.uiState, popBackStack = popBackStack
    )
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
    var checkPwd by remember {
        mutableStateOf(TextFieldValue(""))
    }

    var isNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPwdError by remember { mutableStateOf(false) }
    var isCheckPwdError by remember {
        mutableStateOf(false)
    }

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
                TextField(
                    isError = isNameError,
                    label = {
                        Text(text = stringResource(id = R.string.name))
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent, errorIndicatorColor = Color.Red
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp)
                        .background(Color.Transparent),
                    value = name,
                    onValueChange = { newText ->
                        isNameError = true
                        name = newText
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.name))
                    },
                )
                TextField(
                    isError = isEmailError,
                    label = {
                        Text(text = stringResource(id = R.string.email))
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp)
                        .background(Color.Transparent),
                    value = email,
                    onValueChange = { emailValue ->
                        email = emailValue
                        if (emailValue.text == "" || Patterns.EMAIL_ADDRESS.matcher(
                                emailValue.text
                            ).matches()
                        ) {
                            isEmailError = false
                            return@TextField
                        }
                        isEmailError = true
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.placeholder_email))
                    },
                )
                TextField(
                    isError = isPwdError,
                    label = {
                        Text(text = stringResource(id = R.string.password))
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp)
                        .background(Color.Transparent),
                    value = pwd,
                    onValueChange = { textValue ->
                        pwd = textValue
                        if (Pattern.matches("^[a-zA-Z0-9]*\$", pwd.text)
                            && pwd.text.length > 7
                            && pwd.text.length < 17
                        ) {
                            isPwdError = false
                            return@TextField
                        }
                        isPwdError = true
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.password))
                    })
                TextField(
                    label = {
                        Text(text = stringResource(id = R.string.check_password))
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 10.dp)
                        .background(Color.Transparent),
                    value = checkPwd,
                    onValueChange = { textValue ->
                        checkPwd = textValue
                        if (Pattern.matches("^[a-zA-Z0-9]*\$", checkPwd.text)
                            && checkPwd.text.length > 7
                            && checkPwd.text.length < 17
                        ) {
                            isCheckPwdError = false
                            return@TextField
                        }
                        isCheckPwdError = true
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.check_password))
                    },
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
