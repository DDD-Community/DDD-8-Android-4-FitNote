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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.component.defaultBorder
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray2
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import kotlin.math.roundToInt

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
        topBarTitleFontSize = 20.sp,
        onClickTopBarNavigationIcon = popBackStack
    ) {
        Column(modifier = Modifier.padding(it)) {
            HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)

            uiState.exercises.forEachIndexed { exerciseIndex, exercise ->
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)

                ExerciseColumn(
                    exercise,
                    onClickMainCheckbox = {
                        onClickMainCheckbox(exerciseIndex)
                    },
                    onClickSetCheckbox = { index ->
                        onClickSetCheckbox(exerciseIndex, index)
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
private fun ExerciseColumn(
    exercise: MemberLessonUiState.Exercise,
    onClickMainCheckbox: () -> Unit,
    onClickSetCheckbox: (Int) -> Unit,
    onChangeWeight: (String, Int) -> Unit,
    onChangeCount: (String, Int) -> Unit,
) {
    var fold by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(exercise.isDone) {
        fold = exercise.isDone
    }

    val paddingValues = PaddingValues(
        horizontal = LocalFitNoteSpacing.current.spacing4,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(paddingValues)
            .defaultBorder()
            .padding(LocalFitNoteSpacing.current.spacing4),
    ) {
        ExerciseTitle(
            name = exercise.name,
            onClickIcon = {},
        )
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
        ExerciseSetMain(
            stringResource(id = R.string.some_set, exercise.numberOfSets),
            stringResource(id = R.string.some_weight, exercise.mainWeight.format()),
            stringResource(id = R.string.some_count, exercise.mainCount),
            isDone = exercise.isDone,
        ) {
            onClickMainCheckbox()
        }
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
        Divider(color = GrayScaleLightGray1)

        if (fold) {
            HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
            ExerciseUnfold(
                onClickFold = {
                    fold = !fold
                },
            )
        } else {
            exercise.sets.forEachIndexed { index, exerciseSet ->
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
                ExerciseSetItem(
                    exerciseSet = exerciseSet,
                    onClickCheckbox = {
                        onClickSetCheckbox(index)
                    },
                    onChangeWeight = { value ->
                        onChangeWeight(value, index)
                    },
                    onChangeCount = { value ->
                        onChangeCount(value, index)
                    },
                )
            }
            HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
            ExerciseFold(
                onClickFold = {
                    fold = !fold
                },
            )
        }
    }
}

@Composable
fun ExerciseUnfold(
    onClickFold: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClickFold()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.show_each_set),
            color = GrayScaleMidGray3,
            style = LocalFitNoteTypography.current.buttonSmall
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            tint = GrayScaleMidGray2,
            contentDescription = null,
        )
    }
}

@Composable
fun ExerciseFold(
    onClickFold: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClickFold()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.string_fold),
            color = GrayScaleMidGray3,
            style = LocalFitNoteTypography.current.buttonSmall
        )
        Icon(
            imageVector = Icons.Filled.KeyboardArrowUp,
            tint = GrayScaleMidGray2,
            contentDescription = null,
        )
    }
}

@Composable
internal fun ExerciseSetItem(
    exerciseSet: MemberLessonUiState.Exercise.ExerciseSet,
    onClickCheckbox: () -> Unit,
    onChangeWeight: (String) -> Unit,
    onChangeCount: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ExerciseSetItemText(
            text = stringResource(id = R.string.some_set, exerciseSet.setIndex)
        )
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)

        val modifier = Modifier
            .weight(1F)
            .clip(RoundedCornerShape(5.dp))
            .background(GrayScaleLightGray1)
            .padding(
                start = LocalFitNoteSpacing.current.spacing4,
                top = 6.dp,
                bottom = 6.dp,
            )

        ExerciseSetItemTextField(
            modifier = modifier,
            text = exerciseSet.weight.format(),
            suffix = "kg",
        ) {
            onChangeWeight(it)
        }
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)

        ExerciseSetItemTextField(
            modifier = modifier,
            text = exerciseSet.count.format(),
            suffix = "회",
        ) {
            onChangeCount(it)
        }
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)

        Checkbox(
            checked = exerciseSet.isDone,
            onCheckedChange = { onClickCheckbox() }
        )
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

private fun Int.format(): String = this.toString().format()
private fun Double.format(): String = this.toString().format()

private fun String.format(originalText: String = "0"): String {
    return if (this.isEmpty()) {
        "0"
    } else {
        this.toIntOrNull()?.toString()
            ?: this.toDoubleOrNull()?.let {
//                if (it % 1 == 0.0) {
//                    it.roundToInt()
//                } else {
                ((it * 100).roundToInt() / 100)
//                }
                    .toString()
            } ?: originalText
    }
}

@Composable
fun ExerciseSetItemTextField(
    modifier: Modifier,
    text: String,
    suffix: String,
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        value = text,
        onValueChange = { textValue ->
            onValueChange(textValue.format(text))
        },
        modifier = modifier,
        textStyle = LocalFitNoteTypography.current.buttonMedium,
        visualTransformation = SuffixVisualTransformation(suffix),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        maxLines = 1,

        )
//    color = GrayScaleMidGray3,
}

@Composable
fun ExerciseSetMain(
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
        MemberLessonUiState.Exercise(
            name = "벤치프레스",
            sets = listOf(
                MemberLessonUiState.Exercise.ExerciseSet(
                    setIndex = 1,
                    weight = 10.0,
                    count = 30,
                    isDone = false
                ),
                MemberLessonUiState.Exercise.ExerciseSet(
                    setIndex = 2,
                    weight = 15.0,
                    count = 25,
                    isDone = false
                ),
                MemberLessonUiState.Exercise.ExerciseSet(
                    setIndex = 3,
                    weight = 20.0,
                    count = 20,
                    isDone = false
                ),
                MemberLessonUiState.Exercise.ExerciseSet(
                    setIndex = 4,
                    weight = 40.0,
                    count = 15,
                    isDone = false
                ),
            ),
            isDone = false,
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
