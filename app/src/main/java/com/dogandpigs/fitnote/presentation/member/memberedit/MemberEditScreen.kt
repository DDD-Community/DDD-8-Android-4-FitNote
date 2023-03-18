package com.dogandpigs.fitnote.presentation.member.memberedit

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.member.MemberUiState
import com.dogandpigs.fitnote.presentation.ui.component.BottomPositiveButton
import com.dogandpigs.fitnote.presentation.member.component.MemberInfoList
import com.dogandpigs.fitnote.presentation.ui.component.DefaultDatePickerDialog
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing

@Composable
internal fun MemberEditScreen(
    viewModel: MemberEditViewModel = hiltViewModel(),
    memberId: Int,
    popBackStack: () -> Unit,
    navigateToMemberDetail: (Int) -> Unit,
) {
    val context = LocalContext.current
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initialize(
            memberId = memberId,
        )
    }

    LaunchedEffect(uiState.isNext) {
        if (uiState.isNext) {
            Toast.makeText(context, "수정이 완료되었습니다.", Toast.LENGTH_SHORT).show()
            navigateToMemberDetail(memberId)
        }
    }

    LaunchedEffect(uiState.toast) {
        if (uiState.toast.isNotBlank()) {
            Toast.makeText(context, uiState.toast, Toast.LENGTH_SHORT).show()
        }
    }

    MemberEdit(
        uiState = uiState,
        onClickBackButton = popBackStack,
        onClickAddButton = viewModel::editMember,
        onChangeName = viewModel::setName,
        onClickGender = viewModel::setGender,
        onChangeHeight = viewModel::setHeight,
        onChangeWeight = viewModel::setWeight,
        onChangeDate = viewModel::setDateMillis,
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
    onChangeDate: (Long?) -> Unit,
) {
    val datePickerVisible = remember { mutableStateOf(false) }

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
                    onClickDate = { datePickerVisible.value = true },
                )
            }

            BottomPositiveButton(
                text = stringResource(id = R.string.modification_completion),
                onClick = onClickAddButton,
            )

            DefaultDatePickerDialog(
                visible = datePickerVisible.value,
                onDismissRequest = { datePickerVisible.value = false },
                onClickConfirmButton = onChangeDate,
                dateMilliSeconds = uiState.dateMillis,
            )
        }
    }
}

private val previewUiState = MemberUiState(
    name = "",
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
            onChangeDate = {},
        )
    }
}
