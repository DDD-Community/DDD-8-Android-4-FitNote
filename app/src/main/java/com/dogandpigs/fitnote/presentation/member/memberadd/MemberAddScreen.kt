package com.dogandpigs.fitnote.presentation.member.memberadd

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
internal fun MemberAddScreen(
    viewModel: MemberAddViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToMemberListWithRegistration: (Boolean) -> Unit,
) {
    val context = LocalContext.current
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.isNext) {
        if (uiState.isNext) {
            navigateToMemberListWithRegistration(true)
        }
    }

    LaunchedEffect(uiState.toast) {
        if (uiState.toast.isNotBlank()) {
            Toast.makeText(context, uiState.toast, Toast.LENGTH_SHORT).show()
        }
    }

    MemberAdd(
        uiState = uiState,
        onClickBackButton = navigateToHome,
        onClickAddButton = viewModel::addMember,
        onChangeName = viewModel::setName,
        onClickGender = viewModel::setGender,
        onChangeHeight = viewModel::setHeight,
        onChangeWeight = viewModel::setWeight,
    )
}

@Composable
private fun MemberAdd(
    uiState: MemberUiState,
    onClickBackButton: () -> Unit,
    onClickAddButton: () -> Unit,
    onChangeName: (String) -> Unit,
    onClickGender: (MemberUiState.Gender) -> Unit,
    onChangeHeight: (String) -> Unit,
    onChangeWeight: (String) -> Unit,
) {
    FitNoteScaffold(
        topBarTitle = stringResource(id = R.string.member_registration),
        onClickTopBarNavigationIcon = onClickBackButton,
        onBackButton = onClickBackButton,
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
                text = stringResource(id = R.string.registration),
                onClick = onClickAddButton,
            )
        }
    }
}

private val previewUiState = MemberUiState(name = "김코치", profileImgUrl = "")

@ComponentPreview
@Composable
private fun PreviewMemberAdd() {
    FitNoteTheme {
        MemberAdd(
            uiState = previewUiState,
            onClickAddButton = {},
            onClickBackButton = {},
            onChangeName = {},
            onClickGender = {},
            onChangeHeight = {},
            onChangeWeight = {},
        )
    }
}
