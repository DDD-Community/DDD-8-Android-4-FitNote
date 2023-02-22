package com.dogandpigs.fitnote.presentation.member.memberdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.DefaultTwoButton
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MemberDetailScreen(
    viewModel: MemberDetailViewModel = hiltViewModel(),
    memberId: Long,
    popBackStack: () -> Unit,
    navigateToMemberEdit: (Long) -> Unit,
    navigateToMemberLessonList: (Long) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initialize(
            memberId = memberId,
        )
    }

    MemberDetail(
        uiState = uiState,
        popBackStack = popBackStack,
        onClickInformationModificationButton = {
            navigateToMemberEdit(memberId)
        },
        onClickLessonListButton = {
            navigateToMemberLessonList(memberId)
        },
    )
}

@Composable
private fun MemberDetail(
    uiState: MemberDetailUiState,
    popBackStack: () -> Unit,
    onClickInformationModificationButton: () -> Unit,
    onClickLessonListButton: () -> Unit,
) {
    FitNoteScaffold(
        topBarTitle = String.format(
            stringResource(id = R.string.some_member),
            uiState.name
        ),
        onClickTopBarNavigationIcon = popBackStack,
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing6)

                ItemList(
                    uiState = uiState,
                )
            }

            BottomButtons(
                onClickNegativeButton = {
                    onClickInformationModificationButton()
                },
                onClickPositiveButton = {
                    onClickLessonListButton()
                },
            )
        }
    }
}

@Composable
private fun ItemList(
    uiState: MemberDetailUiState,
) {
    Column {
        val items = listOf(
            stringResource(id = R.string.registration_date) to uiState.date,
            stringResource(id = R.string.gender) to uiState.gender,
            stringResource(id = R.string.height) to uiState.height,
            stringResource(id = R.string.weight) to uiState.weight,
        )
        Column {
            for (item in items) {
                TextRow(
                    title = item.first,
                    content = item.second,
                )
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
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

@Composable
private fun BottomButtons(
    onClickNegativeButton: () -> Unit,
    onClickPositiveButton: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        DefaultTwoButton(
            negativeText = stringResource(id = R.string.information_modification),
            onClickNegative = onClickNegativeButton,
            negativePaddingValues = PaddingValues(
                start = LocalFitNoteSpacing.current.spacing4,
            ),
            negativeTextStyle = LocalFitNoteTypography.current.buttonDefault,
            positiveText = stringResource(id = R.string.lesson_list),
            onClickPositive = onClickPositiveButton,
            positivePaddingValues = PaddingValues(
                end = LocalFitNoteSpacing.current.spacing4,
            ),
            positiveTextStyle = LocalFitNoteTypography.current.buttonDefault,
            spaceBetweenButtons = LocalFitNoteSpacing.current.spacing4,
        )
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
    }
}

private val previewUiState = MemberDetailUiState(
    name = "나초보",
    date = "2022년 11월 18일",
    gender = "남성",
    height = "165cm",
    weight = "52kg",
)

@ComponentPreview
@Composable
private fun PreviewMemberDetail() {
    FitNoteTheme {
        MemberDetail(
            uiState = previewUiState,
            popBackStack = {},
            onClickInformationModificationButton = {},
            onClickLessonListButton = {},
        )
    }
}
