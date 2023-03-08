package com.dogandpigs.fitnote.presentation.join

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.CompleteButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTextField
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.passwordVisualTransformation
import com.dogandpigs.fitnote.presentation.ui.theme.Alert
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@Composable
internal fun JoinScreen(
    viewModel: JoinViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToLogin: (String) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isJoinSuccess) {
        Log.d("test", "JoinScreen: ")
        if (state.isJoinSuccess) {
            navigateToLogin(state.email)
            /**
             * return 추가하면 매우 깜빡임..
             * */
            //        return
        }
    }

    Join(
        uiState = state,
        checkEmail = viewModel::checkEmail,
        checkPassword = viewModel::checkPassword,
        checkCheckPassword = viewModel::checkCheckPassword,
        check = viewModel::check,
        setUiState = viewModel::setUiState,
        join = viewModel::join,
        popBackStack = popBackStack,
    )
}

@Composable
private fun Join(
    uiState: JoinUiState,
    checkEmail: (String) -> Boolean,
    checkPassword: (String) -> Boolean,
    checkCheckPassword: (password: String, checkPassword: String) -> Boolean,
    check: (Boolean) -> Unit,
    setUiState: (JoinViewModel.JoinUiState) -> Unit,
    join: () -> Unit,
    popBackStack: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }
    var checkPwd by remember { mutableStateOf("") }

    var isEmailError by remember { mutableStateOf(false) }
    var isPwdError by remember { mutableStateOf(false) }
    var isCheckPwdError by remember { mutableStateOf(false) }

    FitNoteScaffold(
        topBarTitle = stringResource(id = R.string.btn_join),
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
                    value = name,
                    onValueChange = { newText ->
                        // TODO 정규식 isNameError = true
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
                    onValueChange = { newEmail ->
                        email = newEmail
                        isEmailError = checkEmail(email)
                        check(!isEmailError && !isPwdError && !isCheckPwdError)
                    },
                    labelText = stringResource(id = R.string.email),
                    placeholderText = stringResource(id = R.string.placeholder_email),
                    supportingText = {
                        if (isEmailError) {
                            DefaultText(
                                text = "이메일 형식에 맞게 입력해주세요",
                                color = Alert,
                                style = LocalFitNoteTypography.current.textSmall,
                            )
                        }
                    },
                )
                /**
                 * 비밀번호
                 */
                DefaultTextField(
                    isError = isPwdError,
                    value = pwd,
                    onValueChange = { newPwd ->
                        pwd = newPwd
                        isPwdError = checkPassword(pwd)
                        check(!isEmailError && !isPwdError && !isCheckPwdError)
                    },
                    labelText = stringResource(id = R.string.password),
                    placeholderText = stringResource(id = R.string.password),
                    visualTransformation = passwordVisualTransformation,
                    supportingText = {
                        if (isPwdError) {
                            DefaultText(
                                text = "8-16자의 영문/숫자 조합으로 공백없이 입력해주세요",
                                color = Alert,
                                style = LocalFitNoteTypography.current.textSmall,
                            )
                        }
                    },
                )
                /**
                 * 비밀번호 확인
                 */
                DefaultTextField(
                    isError = isCheckPwdError,
                    value = checkPwd,
                    onValueChange = { newCheckPwd ->
                        checkPwd = newCheckPwd
                        isCheckPwdError = checkCheckPassword(pwd, checkPwd)
                        check(!isEmailError && !isPwdError && !isCheckPwdError)
                    },
                    labelText = stringResource(id = R.string.check_password),
                    placeholderText = stringResource(id = R.string.check_password),
                    visualTransformation = passwordVisualTransformation,
                    supportingText = {
                        if (isCheckPwdError) {
                            DefaultText(
                                text = "비밀번호가 일치하지 않습니다",
                                color = Alert,
                                style = LocalFitNoteTypography.current.textSmall,
                            )
                        }
                    },
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                JoinInfo()
                HeightSpacer(height = 99.dp)
            }

            CompleteButton(
                text = stringResource(id = R.string.completion),
                isReadyComplete = uiState.isReadyJoin,
                onClick = {
                    setUiState(
                        JoinViewModel.JoinUiState(
                            name = name,
                            email = email,
                            firstPassword = pwd,
                            verifyPassword = checkPwd,
                        )
                    )
                    join()
                },
            )
        }
    }
}

@Composable
private fun JoinInfo() {
    val linkText = stringResource(id = R.string.personal_policy)
    val tag = "URL"
    val url = "https://fitnote.notion.site/fitnote/f6b7a4feb0fc4c13b5a860baa82006e0"

    val annotatedLinkString: AnnotatedString = buildAnnotatedString {
        val text = stringResource(id = R.string.terms_and_conditions)
        val startIndex = text.indexOf(linkText)
        val endIndex = startIndex + linkText.length
        append(text)
        addStyle(
            style = LocalFitNoteTypography.current.textSmall
                .toSpanStyle()
                .copy(
                    color = BrandPrimary,
                    textDecoration = TextDecoration.Underline,
                ),
            start = startIndex,
            end = endIndex,
        )

        addStringAnnotation(
            tag = tag,
            annotation = url,
            start = startIndex,
            end = endIndex,
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        text = annotatedLinkString,
        style = LocalFitNoteTypography.current.textSmall + TextStyle(textAlign = TextAlign.Center),
        onClick = {
            annotatedLinkString
                .getStringAnnotations(tag, it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        },
    )
}

private val mockUiState = JoinUiState()

@FigmaPreview
@Composable
private fun PreviewJoin() {
    FitNoteTheme {
        Join(
            uiState = mockUiState,
            checkEmail = { true },
            checkPassword = { true },
            checkCheckPassword = { _: String, _: String -> true },
            check = {},
            setUiState = {},
            join = {},
            popBackStack = {},
        )
    }
}
