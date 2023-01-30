package com.dogandpigs.fitnote.presentation.member.memberadd

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.ui.component.DefaultBottomLargePositiveButton
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

    LaunchedEffect(uiState.isAddSuccess) {
        if (uiState.isAddSuccess) {
            navigateToMemberListWithRegistration(true)
        }
    }
    MemberAdd(
        uiState = uiState,
        viewModel = viewModel,
        popBackStack = popBackStack,
        onClickAddButton = {
            viewModel.addMember()
        }
    )
}

@Composable
private fun MemberAdd(
    uiState: MemberAddUiState,
    viewModel: MemberAddViewModel,
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

                TextFieldList(viewModel)
            }

            MemberAddButton(
                onClick = onClickAddButton,
            )
        }
    }
}

@Composable
private fun TextFieldList(viewModel: MemberAddViewModel) {
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
                viewModel.setName(newText)
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
                if (it == "남성") {
                    viewModel.setGender(gender = 1)
                } else if(it == "여성") {
                    viewModel.setGender(gender = 0)
                }
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
                viewModel.setHeight(it.toInt())
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
                viewModel.setWeight(it.toInt())
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
    DefaultBottomLargePositiveButton(
        positiveText = stringResource(id = R.string.registration),
        onClickPositive = onClick,
    ) {
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
    }
}

private val previewUiState = MemberAddUiState(name = "김코치", profileImgUrl = "")

@ComponentPreview
@Composable
private fun PreviewMemberAdd() {
    FitNoteTheme {
        MemberAdd(
            uiState = previewUiState,
            viewModel = hiltViewModel(),
            onClickAddButton = {},
            popBackStack = {},
        )
    }
}
