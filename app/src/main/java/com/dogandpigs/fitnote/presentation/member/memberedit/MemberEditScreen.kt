package com.dogandpigs.fitnote.presentation.member.memberedit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.member.MemberUiState
import com.dogandpigs.fitnote.presentation.member.component.MemberButton
import com.dogandpigs.fitnote.presentation.member.component.MemberInfoList
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MemberEditScreen(
    viewModel: MemberEditViewModel = hiltViewModel(),
    memberId: Long,
    popBackStack: () -> Unit,
    navigateToMemberDetail: (Long) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isNext) {
        if (uiState.isNext) {
            navigateToMemberDetail(memberId)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.initialize(
            memberId = memberId,
        )
    }

    MemberEdit(
        uiState = uiState,
        onClickBackButton = popBackStack,
        onClickAddButton = viewModel::editMember,
        onChangeName = viewModel::setName,
        onClickGender = viewModel::setGender,
        onChangeHeight = viewModel::setHeight,
        onChangeWeight = viewModel::setWeight,
    )
}

@Composable
private fun MemberEdit(
    uiState: MemberUiState,
    onClickBackButton: () -> Unit,
    onClickAddButton: () -> Unit,
    onChangeName: (String) -> Unit,
    onClickGender: (MemberUiState.Gender) -> Unit,
    onChangeHeight: (String) -> Unit,
    onChangeWeight: (String) -> Unit,
) {
    FitNoteScaffold(
        topBarTitle = stringResource(id = R.string.information_modification),
        onClickTopBarNavigationIcon = onClickBackButton,
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)

                MemberInfoList(
                    uiState = uiState,
                    onChangeName = onChangeName,
                    onChangeGender = onClickGender,
                    onChangeHeight = onChangeHeight,
                    onChangeWeight = onChangeWeight,
                )
            }

            MemberButton(
                text = stringResource(id = R.string.modification_completion),
                onClick = onClickAddButton,
            )
        }
    }
}

private val previewUiState = MemberUiState(
    name = "",
    createDate = "2022년 11월 18일",
    gender = MemberUiState.Gender.MALE,
    height = "165.0",
    weight = "52.0",
)

@ComponentPreview
@Composable
private fun PreviewMemberEdit() {
    FitNoteTheme {
        MemberEdit(
            uiState = previewUiState,
            onClickBackButton = {},
            onClickAddButton = {},
            onChangeName = {},
            onClickGender = {},
            onChangeHeight = {},
            onChangeWeight = {},
        )
    }
}
