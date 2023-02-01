package com.dogandpigs.fitnote.presentation.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun SettingScreen(
    viewModel: SettingViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    onClickLogout: () -> Unit = {},
    onClickWithdrawal: () -> Unit = {},
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    Setting(
        uiState = uiState,
        popBackStack = popBackStack,
        onClickLogout = onClickLogout,
        onClickWithdrawal = onClickWithdrawal,
    )
}

@Composable
private fun Setting(
    uiState: SettingUiState,
    popBackStack: () -> Unit,
    onClickLogout: () -> Unit,
    onClickWithdrawal: () -> Unit,
) {
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
                        text = stringResource(id = R.string.logout),
                        color = GrayScaleMidGray2,
                        style = LocalFitNoteTypography.current.textDefault,
                    )
                    HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
                    DefaultText(
                        text = stringResource(id = R.string.withdraw),
                        color = GrayScaleMidGray2,
                        style = LocalFitNoteTypography.current.textDefault,
                    )
                }
            }
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
