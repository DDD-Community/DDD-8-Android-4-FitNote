package com.dogandpigs.fitnote.presentation.lesson.addlesson

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.MainViewModel
import com.dogandpigs.fitnote.presentation.base.ComponentPreview
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.base.MainEvent
import com.dogandpigs.fitnote.presentation.lesson.Exercise
import com.dogandpigs.fitnote.presentation.lesson.LessonMode
import com.dogandpigs.fitnote.presentation.lesson.component.ExerciseColumn
import com.dogandpigs.fitnote.presentation.lesson.component.LessonCountTextField
import com.dogandpigs.fitnote.presentation.lesson.component.LessonSetTextField
import com.dogandpigs.fitnote.presentation.lesson.component.LessonTextField
import com.dogandpigs.fitnote.presentation.lesson.component.LessonWeightTextField
import com.dogandpigs.fitnote.presentation.ui.component.BottomPositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultDatePickerDialog
import com.dogandpigs.fitnote.presentation.ui.component.DefaultSpacer
import com.dogandpigs.fitnote.presentation.ui.component.DefaultText
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.HeightSpacer
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.component.defaultBorder
import com.dogandpigs.fitnote.presentation.ui.theme.BrandPrimary
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleDarkGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleLightGray1
import com.dogandpigs.fitnote.presentation.ui.theme.GrayScaleMidGray3
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteSpacing
import com.dogandpigs.fitnote.presentation.ui.theme.LocalFitNoteTypography
import com.dogandpigs.fitnote.presentation.ui.theme.SubPrimary
import com.dogandpigs.fitnote.presentation.util.format

@Composable
internal fun AddLessonScreen(
    viewModel: AddLessonViewModel = hiltViewModel(),
    mainViewModel: MainViewModel,
    memberId: Int,
    lessonId: Int = 0, // TODO
    mode: LessonMode = LessonMode.ADD,
    popBackStack: () -> Unit,
    navigateToLoadLesson: () -> Unit,
    navigateToMemberLessonList: (Int) -> Unit,
) {
    val context = LocalContext.current

    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initialize(
            memberId = memberId,
            lessonId = lessonId,
            mode = mode,
        )

        viewModel.eventSharedFlow.collect {
            when (it) {
                AddLessonEvent.None -> {}
                is AddLessonEvent.Toast -> {
                    val message = it.message
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
                is AddLessonEvent.CustomToast -> {
                    mainViewModel.eventCustomToast(
                        MainEvent.CustomToast(
                            message = it.message
                        )
                    )
                }
            }
        }
    }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            // TODO 토스트 안내 제공
            navigateToMemberLessonList(memberId)
        }
    }

    AddLesson(
        uiState = uiState,
        changeExerciseName = viewModel::changeExerciseName,
        addExerciseSet = viewModel::addExerciseSet,
        removeExerciseSet = viewModel::removeExerciseSet,
        onChangeWeight = viewModel::changeWeight,
        onChangeCount = viewModel::changeCount,
        onSaveButtonClick = viewModel::saveLesson,
        onClickClose = popBackStack,
        onClickLoadLesson = navigateToLoadLesson,
        onClickAddExercise = viewModel::addExercise,
        onChangeDate = viewModel::setDateMilliSeconds,
        onChangeAllSet = viewModel::changeAllSet,
        onChangeAllWeight = viewModel::changeAllWeight,
        onChangeAllCount = viewModel::changeAllCount,
        onExerciseSetDelete = viewModel::removeExercise,
    )
}

@Composable
private fun AddLesson(
    uiState: AddLessonUiState,
    changeExerciseName: (
        index: Int,
        name: String,
    ) -> Unit,
    addExerciseSet: (index: Int) -> Unit,
    removeExerciseSet: (
        exerciseIndex: Int,
        exerciseSetIndex: Int,
    ) -> Unit,
    onChangeWeight: (String, Int, Int) -> Unit,
    onChangeCount: (String, Int, Int) -> Unit,
    onSaveButtonClick: () -> Unit,
    onClickClose: () -> Unit,
    onClickLoadLesson: () -> Unit,
    onClickAddExercise: () -> Unit,
    onChangeDate: (Long?) -> Unit,
    onChangeAllSet: (
        exerciseIndex: Int, set: String
    ) -> Unit,
    onChangeAllWeight: (
        exerciseIndex: Int, weight: String
    ) -> Unit,
    onChangeAllCount: (
        exerciseIndex: Int, weight: String
    ) -> Unit,
    onExerciseSetDelete: (index: Int) -> Unit,
) {
    val datePickerVisible = remember { mutableStateOf(false) }

    FitNoteScaffold(
        topBarTitle = when (uiState.mode) {
            LessonMode.EDIT -> {
                stringResource(id = R.string.lesson_edit)
            }
            else -> {
                stringResource(id = R.string.lesson_add)
            }
        },
        onClickTopBarNavigationIcon = onClickClose,
        topBarNavigationIconImageVector = Icons.Filled.Close,
        topBarActions = {
            TextButton(onClick = onClickLoadLesson) {
                DefaultText(
                    text = stringResource(id = R.string.load),
                    color = BrandPrimary,
                    style = LocalFitNoteTypography.current.buttonMedium
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .background(Color.White),
        ) {
            val paddingValues = PaddingValues(16.dp)
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(color = Color.White)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)

                DateLabel(
                    dateString = uiState.dateString,
                    onClick = { datePickerVisible.value = true },
                )

                HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)

                uiState.exercises.forEachIndexed { index, exercise ->
                    ExerciseColumn(
                        exercise = exercise,
                        Title = {
                            ItemMainExerciseName(
                                text = exercise.name,
                                onChangeName = { newValue ->
                                    changeExerciseName(index, newValue)
                                },
                                onExerciseSetDelete = {
                                    onExerciseSetDelete(index)
                                },
                            )
                            HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)

                            ItemMainExerciseRow(
                                exercise = exercise,
                                onChangeAllSet = { set ->
                                    onChangeAllSet(index, set)
                                },
                                onChangeAllWeight = { weight ->
                                    onChangeAllWeight(index, weight)
                                },
                                onChangeAllCount = { count ->
                                    onChangeAllCount(index, count)
                                },
                            )

                            HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
                        },
                        foldText = stringResource(id = R.string.edit_per_set),
                        ItemButton = { exerciseSetIndex: Int, _: Exercise.ExerciseSet ->
                            IconButton(
                                onClick = {
                                    removeExerciseSet(index, exerciseSetIndex)
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.trash),
                                    stringResource(id = R.string.delete),
                                )
                            }
                        },
                        BottomButton = {
                            PlusSetButton(
                                onClick = {
                                    addExerciseSet(index)
                                }
                            )
                        },
                        onChangeWeight = { newValue: String, exerciseSetIndex: Int ->
                            onChangeWeight(newValue, index, exerciseSetIndex)
                        },
                        onChangeCount = { newValue: String, exerciseSetIndex: Int ->
                            onChangeCount(newValue, index, exerciseSetIndex)
                        },
                    )
                    HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
                }

                HeightSpacer(height = 18.dp)
                AddExerciseButton(
                    onClickAddExercise = onClickAddExercise,
                )
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing9)
            }

            BottomPositiveButton(
                text = stringResource(id = R.string.btn_save),
                enabled = uiState.exercises.isNotEmpty(),
                onClick = onSaveButtonClick,
            )

            DefaultDatePickerDialog(
                visible = datePickerVisible.value,
                onDismissRequest = { datePickerVisible.value = false },
                onClickConfirmButton = onChangeDate,
                dateMilliSeconds = uiState.dateMilliSeconds,
            )
        }
    }
}

@Composable
private fun DateLabel(
    dateString: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(58.dp)
            .fillMaxWidth()
            .defaultBorder()
            .padding(16.dp, 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DefaultText(
            text = stringResource(id = R.string.txt_date),
            color = Color.Black,
            style = LocalFitNoteTypography.current.titleDefault
        )
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing5)
        ClickableText(text = AnnotatedString(dateString),
            style = LocalFitNoteTypography.current.textDefault,
            onClick = {
                onClick()
            }
        )
    }
}

@Composable
private fun ItemMainExerciseRow(
    exercise: Exercise,
    onChangeAllSet: (set: String) -> Unit,
    onChangeAllWeight: (String) -> Unit,
    onChangeAllCount: (String) -> Unit,
) {
    val set = remember { mutableStateOf(exercise.numberOfSets.toString()) }
    LaunchedEffect(exercise.numberOfSets) {
        set.value = exercise.numberOfSets.toString()
    }

    val weight = remember { mutableStateOf(exercise.mainWeight.format()) }
    LaunchedEffect(exercise.mainWeight) {
        weight.value = exercise.mainWeight.format()
    }

    val count = remember { mutableStateOf(exercise.mainCount.toString()) }
    LaunchedEffect(exercise.mainCount) {
        count.value = exercise.mainCount.toString()
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LessonSetTextField(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            text = set.value,
            onValueChange = { newValue ->
                if (newValue.isNotBlank()) {
                    onChangeAllSet(newValue)
                } else {
                    onChangeAllSet("0")
                }
                set.value = newValue
            },
        )
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)

        LessonWeightTextField(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            text = weight.value,
            onValueChange = { newValue ->
                // TODO 아래 세트별 운동에도 String 적용
                if (newValue.isNotBlank()) {
                    onChangeAllWeight(newValue)
                } else {
                    onChangeAllWeight("0")
                }
                weight.value = newValue
            },
        )
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)

        LessonCountTextField(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            text = count.value,
            onValueChange = { newValue ->
                if (newValue.isNotBlank()) {
                    onChangeAllCount(newValue)
                } else {
                    onChangeAllCount("0")
                }
                count.value = newValue
            },
        )
    }
}

@Composable
private fun ItemMainExerciseName(
    text: String,
    onChangeName: (String) -> Unit,
    onExerciseSetDelete: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LessonTextField(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            text = text,
            onValueChange = onChangeName,
            placeholder = {
                DefaultText(
                    text = stringResource(id = R.string.exercise_name),
                    color = GrayScaleMidGray3,
                    style = LocalFitNoteTypography.current.buttonMedium,
                )
            }
        )
        DefaultSpacer(width = LocalFitNoteSpacing.current.spacing6)
        IconButton(
            onClick = onExerciseSetDelete
        ) {
            Icon(
                painter = painterResource(id = R.drawable.trash),
                contentDescription = stringResource(id = R.string.delete),
            )
        }
    }
}

@Composable
private fun PlusSetButton(
    onClick: () -> Unit,
) {
    Button(
        colors = ButtonDefaults.outlinedButtonColors(
            disabledContentColor = SubPrimary,
            disabledContainerColor = BrandPrimary,
            contentColor = GrayScaleMidGray3,
            containerColor = GrayScaleLightGray1
        ),
        contentPadding = PaddingValues(16.dp, 6.dp),
        onClick = onClick,
        modifier = Modifier
//            .align(Alignment.CenterHorizontally)
            .fillMaxWidth(),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(text = stringResource(id = R.string.btn_add_set))
    }
}

@Composable
private fun AddExerciseButton(
    onClickAddExercise: () -> Unit
) {
    val stroke = Stroke(
        width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    Box(
        Modifier
            .fillMaxWidth()
            .clickable { onClickAddExercise() },
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            drawRoundRect(
                color = Color.LightGray, style = stroke, cornerRadius = CornerRadius(10.dp.toPx())
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Add, tint = Color.Black, // Icon Color
                contentDescription = stringResource(id = R.string.add_exercise)
            )
            DefaultText(
                text = stringResource(id = R.string.add_exercise),
                color = GrayScaleDarkGray1,
                style = LocalFitNoteTypography.current.buttonMedium,
            )
        }
    }
}

private val mockUiState = AddLessonUiState()

@FigmaPreview
@Composable
private fun PreviewAddLesson() {
    FitNoteTheme {
        AddLesson(
            uiState = mockUiState,
            changeExerciseName = { _: Int, _: String -> },
            addExerciseSet = {},
            removeExerciseSet = { _: Int, _: Int -> },
            onChangeWeight = { _: String, _: Int, _: Int -> },
            onChangeCount = { _: String, _: Int, _: Int -> },
            onSaveButtonClick = {},
            onClickClose = {},
            onClickLoadLesson = {},
            onClickAddExercise = {},
            onChangeDate = {},
            onChangeAllSet = { _: Int, _: String -> },
            onChangeAllWeight = { _: Int, _: String -> },
            onChangeAllCount = { _: Int, _: String -> },
            onExerciseSetDelete = {},
        )
    }
}

@ComponentPreview
@Composable
private fun PreviewExerciseColumn() {
    val previewExercise = mockUiState.exercises.first()
    FitNoteTheme {
        ExerciseColumn(
            exercise = previewExercise,
            Title = {
                ItemMainExerciseName(
                    text = previewExercise.name,
                    onChangeName = {},
                    onExerciseSetDelete = {},
                )
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)

                ItemMainExerciseRow(
                    exercise = previewExercise,
                    onChangeAllSet = {},
                    onChangeAllWeight = {},
                    onChangeAllCount = {},
                )

                HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
            },
            foldText = stringResource(id = R.string.edit_per_set),
            ItemButton = { _: Int, _: Exercise.ExerciseSet ->
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.trash),
                        contentDescription = stringResource(id = R.string.delete),
                    )
                }
            },
            BottomButton = {
                PlusSetButton(
                    onClick = {}
                )
            },
            onChangeWeight = { _: String, _: Int ->
            },
            onChangeCount = { _: String, _: Int ->
            },
        )
    }
}
