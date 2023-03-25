package com.dogandpigs.fitnote.presentation.member.memberadd

import android.annotation.SuppressLint
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.MainViewModel
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.base.MainEvent
import com.dogandpigs.fitnote.presentation.member.MemberUiState
import com.dogandpigs.fitnote.presentation.member.component.MemberInfoList
import com.dogandpigs.fitnote.presentation.ui.component.BottomPositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultDatePickerDialog
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing

@Composable
internal fun MemberAddScreen(
    viewModel: MemberAddViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    navigateToHome: () -> Unit,
) {
    val context = LocalContext.current
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    val message = stringResource(id = R.string.member_add_complete_message)

    LaunchedEffect(uiState.isNext) {
        if (uiState.isNext) {
            mainViewModel.eventCustomToast(
                MainEvent.CustomToast(
                    message = message,
                )
            )
            navigateToHome()
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
        onChangeDate = viewModel::setDateMillis,
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun MemberAdd(
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
                    onClickDate = { datePickerVisible.value = true },
                )
            }

            BottomPositiveButton(
                text = stringResource(id = R.string.registration),
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
            onChangeDate = {},
        )
    }
}
