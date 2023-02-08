package com.dogandpigs.fitnote.presentation.member.memberadd

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.ui.component.DefaultBottomLargePositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTextField
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import com.dogandpigs.fitnote.presentation.ui.theme.SubPrimary

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

                TextFieldList(
                    uiState = uiState,
                    viewModel = viewModel,
                )
            }

            MemberAddButton(
                onClick = onClickAddButton,
            )
        }
    }
}

@Composable
private fun TextFieldList(
    uiState: MemberAddUiState,
    viewModel: MemberAddViewModel,
) {
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    var isDateError by remember { mutableStateOf(false) }

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

        HeightSpacer(height = LocalFitNoteSpacing.current.spacing6)
        GenderComponent(
            selectedGender = uiState.gender,
            onClick = viewModel::setGender,
        )
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing6)

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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

@Composable
private fun GenderComponent(
    selectedGender: MemberAddUiState.Gender,
    onClick: (MemberAddUiState.Gender) -> Unit
) {
    Column(
        modifier = Modifier.padding(
            horizontal = LocalFitNoteSpacing.current.spacing4,
        )
    ) {
        DefaultText(
            text = stringResource(id = R.string.gender),
            color = GrayScaleMidGray2,
            style = LocalFitNoteTypography.current.textSmall,
        )
        HeightSpacer(height = 10.dp)
        Row {
            GenderOutlinedButton(
                gender = MemberAddUiState.Gender.MALE,
                isSelected = (selectedGender == MemberAddUiState.Gender.MALE),
                onClick = onClick,
            )
            WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)
            GenderOutlinedButton(
                gender = MemberAddUiState.Gender.FEMALE,
                isSelected = (selectedGender == MemberAddUiState.Gender.FEMALE),
                onClick = onClick,
            )
        }
    }
}

@Composable
private fun GenderOutlinedButton(
    gender: MemberAddUiState.Gender,
    isSelected: Boolean,
    onClick: (MemberAddUiState.Gender) -> Unit
) {
    OutlinedButton(
        onClick = { onClick(gender) },
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) {
                SubPrimary
            } else {
                GrayScaleLightGray1
            },
        ),
        border = BorderStroke(
            1.dp, if (isSelected) {
                BrandPrimary
            } else {
                GrayScaleLightGray2
            }
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        DefaultText(
            modifier = Modifier.padding(
                horizontal = 12.dp,
                vertical = 6.dp,
            ),
            text = gender.text,
            color = GrayScaleMidGray3,
            style = LocalFitNoteTypography.current.buttonSmall,
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
