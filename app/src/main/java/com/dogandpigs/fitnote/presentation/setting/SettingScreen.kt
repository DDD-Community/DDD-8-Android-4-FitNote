package com.dogandpigs.fitnote.presentation.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.ui.component.DefaultDialog
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.Alert
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@Composable
internal fun SettingScreen(
    viewModel: SettingViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToSplash: () -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initialize()
    }

    LaunchedEffect(uiState.logout) {
        if (uiState.logout) {
            navigateToSplash()
        }
    }

    LaunchedEffect(uiState.withdrawal) {
        if (uiState.withdrawal) {
            navigateToSplash()
        }
    }

    Setting(
        uiState = uiState,
        popBackStack = popBackStack,
        onClickLogout = viewModel::logout,
        onClickWithdrawal = viewModel::withdrawal,
    )
}

@Composable
private fun Setting(
    uiState: SettingUiState,
    popBackStack: () -> Unit,
    onClickLogout: () -> Unit,
    onClickWithdrawal: () -> Unit,
) {
    val visibleLogoutDialog = remember { mutableStateOf(false) }
    val visibleWithdrawalDialog = remember { mutableStateOf(false) }

    FitNoteScaffold(
        topBarTitle = stringResource(id = R.string.setting),
        onClickTopBarNavigationIcon = popBackStack,
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .padding(LocalFitNoteSpacing.current.spacing4)
                ) {
                    HeightSpacer(height = 3.dp)
                    Image(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = null,
                    )
                    HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
                    TextRow(
                        title = stringResource(id = R.string.name),
                        content = uiState.name,
                    )
                    HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
                    TextRow(
                        title = stringResource(id = R.string.email),
                        content = uiState.email,
                    )
                }

                HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(GrayScaleLightGray2)
                )

                Column(
                    modifier = Modifier
                        .padding(LocalFitNoteSpacing.current.spacing4)
                ) {
                    HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
                    DefaultText(
                        modifier = Modifier.clickable {
                            visibleLogoutDialog.value = true
                        },
                        text = stringResource(id = R.string.logout),
                        color = GrayScaleMidGray2,
                        style = LocalFitNoteTypography.current.textDefault,
                    )
                    HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
                    DefaultText(
                        modifier = Modifier.clickable {
                            visibleWithdrawalDialog.value = true
                        },
                        text = stringResource(id = R.string.withdraw),
                        color = GrayScaleMidGray2,
                        style = LocalFitNoteTypography.current.textDefault,
                    )
                }
            }

            // TODO 다이얼로그 버튼 크기 조절
            LogoutDialog(
                visible = visibleLogoutDialog.value,
                onClickPositive = {
                    visibleLogoutDialog.value = false
                    onClickLogout()
                },
                onClickNegative = {
                    visibleLogoutDialog.value = false
                },
            )
            WithdrawalDialog(
                visible = visibleWithdrawalDialog.value,
                onClickPositive = {
                    visibleWithdrawalDialog.value = false
                    onClickWithdrawal()
                },
                onClickNegative = {
                    visibleWithdrawalDialog.value = false
                },
            )
        }
    }
}

@Composable
private fun TextRow(
    title: String,
    content: String,
) {
    Row {
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)
        Row(
            modifier = Modifier.defaultMinSize(
                minWidth = 86.dp,
            )
        ) {
            DefaultText(
                text = title,
                color = Color.Black,
                style = LocalFitNoteTypography.current.titleDefault,
            )
        }
        DefaultText(
            text = content,
            color = Color.Black,
            style = LocalFitNoteTypography.current.textDefault,
        )
    }
}

@Composable
private fun LogoutDialog(
    visible: Boolean,
    onClickPositive: () -> Unit,
    onClickNegative: () -> Unit,
) {
    DefaultDialog(
        visible = visible,
        onDismissRequest = onClickNegative,
        message = stringResource(id = R.string.logout_message),
        positiveText = stringResource(id = R.string.confirm),
        onPositiveClick = onClickPositive,
        positiveButtonColor = Alert,
        negativeText = stringResource(id = R.string.cancel),
        onNegativeClick = onClickNegative,
    )
}

@Composable
private fun WithdrawalDialog(
    visible: Boolean,
    onClickPositive: () -> Unit,
    onClickNegative: () -> Unit,
) {
    DefaultDialog(
        visible = visible,
        onDismissRequest = onClickNegative,
        message = stringResource(id = R.string.withdraw_message),
        positiveText = stringResource(id = R.string.withdraw),
        onPositiveClick = onClickPositive,
        positiveButtonColor = Alert,
        negativeText = stringResource(id = R.string.cancel),
        onNegativeClick = onClickNegative,
    )
}

private val previewUiState = SettingUiState(
    name = "김코치",
    email = "trainerkim@naver.com",
)

@ComponentPreview
@Composable
private fun PreviewMemberDetail() {
    FitNoteTheme {
        Setting(
            uiState = previewUiState,
            popBackStack = {},
            onClickLogout = {},
            onClickWithdrawal = {},
        )
    }
}
