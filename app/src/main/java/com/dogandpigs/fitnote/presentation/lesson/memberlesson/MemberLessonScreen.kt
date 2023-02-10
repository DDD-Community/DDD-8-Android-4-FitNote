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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.lesson.Exercise
import com.dogandpigs.fitnote.presentation.lesson.ExerciseColumn
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import com.dogandpigs.fitnote.presentation.util.format

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun MemberLessonScreen(
    viewModel: MemberLessonViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToAddLesson: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MemberLessonList(
        uiState = uiState,
        popBackStack = popBackStack,
        onClickAddLesson = navigateToAddLesson,
        navigateToSetting = navigateToSetting,
        onClickMainCheckbox = viewModel::toggleExerciseIsDone,
        onClickSetCheckbox = viewModel::toggleExerciseSetIsDone,
        onChangeWeight = viewModel::changeWeight,
        onChangeCount = viewModel::changeCount,
    )
}

@Composable
private fun MemberLessonList(
    uiState: MemberLessonUiState,
    popBackStack: () -> Unit,
    onClickAddLesson: () -> Unit,
    navigateToSetting: () -> Unit,
    onClickMainCheckbox: (Int) -> Unit,
    onClickSetCheckbox: (Int, Int) -> Unit,
    onChangeWeight: (String, Int, Int) -> Unit,
    onChangeCount: (String, Int, Int) -> Unit,
) {
    FitNoteScaffold(
        topBarTitle = "${
            stringResource(
                id = R.string.some_member,
                uiState.userName
            )
        } ${stringResource(id = R.string.lesson)}",
        onClickTopBarNavigationIcon = popBackStack
    ) {
        Column(modifier = Modifier.padding(it)) {
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
                        Checkbox(
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
        }
    }
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

        Checkbox(
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
            onClickAddLesson = {},
            navigateToSetting = {},
            onClickMainCheckbox = {},
            onClickSetCheckbox = { _: Int, _: Int -> },
            onChangeWeight = { _: String, _: Int, _: Int -> },
            onChangeCount = { _: String, _: Int, _: Int -> },
        )
    }
}
