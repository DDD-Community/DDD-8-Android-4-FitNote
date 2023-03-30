package com.dogandpigs.fitnote.presentation.lesson.memberlesson

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.lesson.Exercise
import com.dogandpigs.fitnote.presentation.lesson.component.ExerciseColumn
import com.dogandpigs.fitnote.presentation.ui.component.BottomPositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultCheckbox
import com.dogandpigs.fitnote.presentation.ui.component.DefaultDialog
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import com.dogandpigs.fitnote.presentation.util.format

@Composable
internal fun MemberLessonScreen(
    viewModel: MemberLessonViewModel = hiltViewModel(),
    memberId: Int,
    lessonDate: Int,
    popBackStack: () -> Unit,
    navigateToSetting: () -> Unit,
    navigateToMemberLessonList: (Int) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initialize(
            memberId = memberId,
            lessonDate = lessonDate,
        )
    }

    LaunchedEffect(uiState.isNext) {
        if (uiState.isNext) {
            navigateToMemberLessonList(memberId)
        }
    }

    MemberLessonList(
        uiState = uiState,
        popBackStack = popBackStack,
        navigateToSetting = navigateToSetting,
        onClickMainCheckbox = viewModel::toggleExerciseIsDone,
        onClickSetCheckbox = viewModel::toggleExerciseSetIsDone,
        onChangeWeight = viewModel::changeWeight,
        onChangeCount = viewModel::changeCount,
        onLessonComplete = viewModel::complete,
    )
}

@Composable
private fun MemberLessonList(
    uiState: MemberLessonUiState,
    popBackStack: () -> Unit,
    navigateToSetting: () -> Unit,
    onClickMainCheckbox: (Int) -> Unit,
    onClickSetCheckbox: (Int, Int) -> Unit,
    onChangeWeight: (String, Int, Int) -> Unit,
    onChangeCount: (String, Int, Int) -> Unit,
    onLessonComplete: () -> Unit,
) {
    val visibleCompleteDialog = remember { mutableStateOf(false) }

    FitNoteScaffold(
        topBarTitle = "${
            stringResource(
                id = R.string.some_member,
                uiState.userName
            )
        } ${stringResource(id = R.string.lesson)}",
        onClickTopBarNavigationIcon = popBackStack
    ) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(it)
                .background(color = Color.White)
                .verticalScroll(scrollState),
        ) {
            HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)

            uiState.exercises.forEachIndexed { exerciseIndex, exercise ->
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)

                ExerciseColumn(
                    exercise,
                    Title = {
                        ExerciseTitle(
                            name = exercise.name,
                            onClickIcon = {},
                        )
                        HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
                        ExerciseSetMain(
                            stringResource(id = R.string.some_set, exercise.numberOfSets),
                            stringResource(id = R.string.some_weight, exercise.mainWeight.format()),
                            stringResource(id = R.string.some_count, exercise.mainCount),
                            isDone = exercise.isFold,
                        ) {
                            onClickMainCheckbox(exerciseIndex)
                        }
                        HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
                    },
                    foldText = stringResource(id = R.string.show_each_set),
                    paddingValues = PaddingValues(
                        horizontal = LocalFitNoteSpacing.current.spacing4,
                    ),
                    ItemButton = { index, exerciseSet ->
                        DefaultCheckbox(
                            checked = exerciseSet.isDone,
                            onCheckedChange = { onClickSetCheckbox(exerciseIndex, index) }
                        )
                    },
                    onChangeWeight = { value, index ->
                        onChangeWeight(value, exerciseIndex, index)
                    },
                    onChangeCount = { value, index ->
                        onChangeCount(value, exerciseIndex, index)
                    },
                )
            }

            HeightSpacer(height = LocalFitNoteSpacing.current.spacing10)
        }

        BottomPositiveButton(
            text = "수업 완료",
            onClick = {
                visibleCompleteDialog.value = true
            },
        )
    }

    CompleteDialog(
        visible = visibleCompleteDialog.value,
        onClickPositive = {
            visibleCompleteDialog.value = false
            onLessonComplete()
        },
        onClickNegative = {
            visibleCompleteDialog.value = false
        },
    )
}

@Composable
fun ExerciseSetItemText(
    text: String,
) {
    Text(
        text = text,
        color = GrayScaleMidGray3,
        style = LocalFitNoteTypography.current.buttonMedium
    )
}

@Composable
private fun ExerciseSetMain(
    vararg text: String,
    isDone: Boolean,
    onClickCheckbox: () -> Unit,
) {
    val paddingValues = PaddingValues(
        horizontal = LocalFitNoteSpacing.current.spacing4,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(5.dp))
            .background(GrayScaleLightGray1),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)
        text.forEach {
            ExerciseSetMainText(
                it,
                paddingValues,
            )
        }

        DefaultCheckbox(
            modifier = Modifier.padding(paddingValues),
            checked = isDone,
            onCheckedChange = { onClickCheckbox() }
        )
    }
}

@Composable
fun ExerciseSetMainText(
    text: String,
    paddingValues: PaddingValues,
) {
    Text(
        text = text,
        modifier = Modifier.padding(paddingValues),
        color = GrayScaleMidGray3,
        style = LocalFitNoteTypography.current.buttonMedium
    )
}

@Composable
private fun ExerciseTitle(
    name: String,
    onClickIcon: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = name,
            modifier = Modifier
                .weight(1F)
                .wrapContentHeight(),
            color = GrayScaleDarkGray2,
            style = LocalFitNoteTypography.current.titleLarge,
            overflow = TextOverflow.Ellipsis,
        )
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing5)
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = null,
            modifier = Modifier.clickable {
                onClickIcon()
            }
        )
    }
}

@Composable
private fun CompleteDialog(
    visible: Boolean,
    onClickPositive: () -> Unit,
    onClickNegative: () -> Unit,
) {
    DefaultDialog(
        visible = visible,
        onDismissRequest = onClickNegative,
        title = stringResource(id = R.string.lesson_complete),
        message = stringResource(id = R.string.lesson_complete_message),
        positiveText = stringResource(id = R.string.confirm),
        onPositiveClick = onClickPositive,
        positiveButtonColor = BrandPrimary,
        negativeText = stringResource(id = R.string.cancel),
        onNegativeClick = onClickNegative,
    )
}

internal val previewUiState = MemberLessonUiState(
    userName = "나초보",
    exercises = listOf(
        Exercise(
            name = "벤치프레스",
            sets = listOf(
                Exercise.ExerciseSet(
                    setIndex = 1,
                    weight = 10.0,
                    count = 30,
                    isDone = false
                ),
                Exercise.ExerciseSet(
                    setIndex = 2,
                    weight = 15.0,
                    count = 25,
                    isDone = false
                ),
                Exercise.ExerciseSet(
                    setIndex = 3,
                    weight = 20.0,
                    count = 20,
                    isDone = false
                ),
                Exercise.ExerciseSet(
                    setIndex = 4,
                    weight = 40.0,
                    count = 15,
                    isDone = false
                ),
            ),
            isFold = false,
        )
    )
)

@FigmaPreview
@Composable
private fun PreviewLesson() {
    FitNoteTheme {
        MemberLessonList(
            uiState = previewUiState,
            popBackStack = {},
            navigateToSetting = {},
            onClickMainCheckbox = {},
            onClickSetCheckbox = { _: Int, _: Int -> },
            onChangeWeight = { _: String, _: Int, _: Int -> },
            onChangeCount = { _: String, _: Int, _: Int -> },
            onLessonComplete = {},
        )
    }
}
