package com.dogandpigs.fitnote.presentation.member.memberedit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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
internal fun MemberEditScreen(
    viewModel: MemberEditViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToMemberListWithRegistration: (Boolean) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    MemberEdit(
        uiState = uiState,
        popBackStack = popBackStack,
        onClickAddButton = {
            navigateToMemberListWithRegistration(true)
        }
    )
}

@Composable
private fun MemberEdit(
    uiState: MemberEditUiState,
    popBackStack: () -> Unit,
    onClickAddButton: () -> Unit,
) {
    FitNoteScaffold(
        topBarTitle = stringResource(id = R.string.information_modification),
        topBarNavigationIconImageVector = Icons.Filled.Close,
        onClickTopBarNavigationIcon = popBackStack,
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)

                TextFieldList(
                    uiState = uiState,
                )
            }

            ButtomButton(
                onClick = onClickAddButton,
            )
        }
    }
}

@Composable
private fun TextFieldList(
    uiState: MemberEditUiState,
) {
    var date by remember { mutableStateOf(uiState.date) }
    var gender by remember { mutableStateOf(uiState.gender) }
    var height by remember { mutableStateOf(uiState.height) }
    var weight by remember { mutableStateOf(uiState.weight) }

    Column {
        /**
         * 등록일 / 2022년 11월 18일
         */
        DefaultTextField(
            value = date,
            onValueChange = {
                date = it
            },
            labelText = stringResource(id = R.string.registration_date),
            placeholderText = "now",
        )
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing6)
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
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing6)
        /**
         * 키 / 165cm -> 100
         */
        DefaultTextField(
            value = height.toString(),
            onValueChange = {
                height = it.toDoubleOrNull() ?: 0.0
            },
            labelText = stringResource(id = R.string.height),
            placeholderText = stringResource(id = R.string.default_height),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing6)
        /**
         * 몸무게 / 52kg -> 52
         */
        DefaultTextField(
            value = weight.toString(),
            onValueChange = {
                weight = it.toDoubleOrNull() ?: 0.0
            },
            labelText = stringResource(id = R.string.weight),
            placeholderText = stringResource(id = R.string.default_weight),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing6)
    }
}

@Composable
private fun ButtomButton(
    onClick: () -> Unit,
) {
    DefaultBottomLargePositiveButton(
        positiveText = stringResource(id = R.string.modification_completion),
        onClickPositive = onClick,
    ) {
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
    }
}

private val previewUiState = MemberEditUiState(
    temp = "",
    name = "",
    date = "2022년 11월 18일",
    gender = "남성",
    height = 165.0,
    weight = 52.0,
)

@ComponentPreview
@Composable
private fun PreviewMemberEdit() {
    FitNoteTheme {
        MemberEdit(
            uiState = previewUiState,
            onClickAddButton = {},
            popBackStack = {},
        )
    }
}
