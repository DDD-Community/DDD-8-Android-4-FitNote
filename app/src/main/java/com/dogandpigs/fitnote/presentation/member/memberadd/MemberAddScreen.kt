package com.dogandpigs.fitnote.presentation.member.memberadd

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.ui.component.DefaultBottomPositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTextField
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MemberAddScreen(
    viewModel: MemberAddViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToMemberListWithRegistration: (Boolean) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    MemberAdd(
        uiState = uiState,
        popBackStack = popBackStack,
        onClickAddButton = {
            navigateToMemberListWithRegistration(true)
        }
    )
}

@Composable
private fun MemberAdd(
    uiState: MemberAddUiState,
    popBackStack: () -> Unit,
    onClickAddButton: () -> Unit,
) {
    FitNoteScaffold(
        topBarTitle = stringResource(id = R.string.member_registration),
        onClickTopBarNavigationIcon = popBackStack,
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)

                TextFieldList()
            }

            MemberAddButton(
                onClick = onClickAddButton,
            )
        }
    }
}

@Composable
private fun TextFieldList() {
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    var isNameError by remember { mutableStateOf(false) }
    var isDateError by remember { mutableStateOf(false) }
    var isGenderError by remember { mutableStateOf(false) }
    var isCheckPwdError by remember { mutableStateOf(false) }

    Column {
        /**
         * 이름
         */
        DefaultTextField(
            value = name,
            onValueChange = { newText ->
                name = newText
            },
            labelText = stringResource(id = R.string.name),
            placeholderText = stringResource(id = R.string.default_name),
        )
        /**
         * 등록일 / 2022년 11월 18일
         */
        DefaultTextField(
            isError = isDateError,
            value = date,
            onValueChange = {
                date = it
            },
            labelText = stringResource(id = R.string.registration_date),
            placeholderText = "now",
        )
        /**
         * 성별 / 남성
         */
        DefaultTextField(
            value = gender,
            onValueChange = {
                gender = it
            },
            labelText = stringResource(id = R.string.gender),
            placeholderText = stringResource(id = R.string.gender_male),
        )
        /**
         * 키 / 165cm -> 100
         */
        DefaultTextField(
            value = height,
            onValueChange = {
                height = it
            },
            labelText = stringResource(id = R.string.height),
            placeholderText = stringResource(id = R.string.default_height),
        )
        /**
         * 몸무게 / 52kg -> 52
         */
        DefaultTextField(
            value = weight,
            onValueChange = {
                weight = it
            },
            labelText = stringResource(id = R.string.weight),
            placeholderText = stringResource(id = R.string.default_weight),
        )
    }
}

@Composable
private fun MemberAddButton(
    onClick: () -> Unit,
) {
    DefaultBottomPositiveButton(
        positiveText = stringResource(id = R.string.registration),
        onClickPositive = onClick,
    ) {
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
    }
}

private val previewUiState = MemberAddUiState(myName = "김코치", profileImgUrl = "")

@ComponentPreview
@Composable
private fun PreviewMemberAdd() {
    FitNoteTheme {
        MemberAdd(
            uiState = previewUiState,
            onClickAddButton = {},
            popBackStack = {},
        )
    }
}
