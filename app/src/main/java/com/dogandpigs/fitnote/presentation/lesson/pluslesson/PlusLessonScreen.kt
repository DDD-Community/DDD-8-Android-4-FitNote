package com.dogandpigs.fitnote.presentation.lesson.pluslesson

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.lesson.Exercise
import com.dogandpigs.fitnote.presentation.lesson.component.ExerciseColumn
import com.dogandpigs.fitnote.presentation.lesson.component.LessonCountTextField
import com.dogandpigs.fitnote.presentation.lesson.component.LessonSetTextField
import com.dogandpigs.fitnote.presentation.lesson.component.LessonTextField
import com.dogandpigs.fitnote.presentation.lesson.component.LessonWeightTextField
import com.dogandpigs.fitnote.presentation.ui.component.BottomPositiveButton
import com.dogandpigs.fitnote.presentation.ui.component.DefaultDatePickerDialog
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
internal fun PlusLessonScreen(
    memberId: Int,
    lessonId: Int = 0, // TODO
    viewModel: PlusLessonViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToLoadLesson: () -> Unit,
    navigateToMemberLessonList: (Int) -> Unit,
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initialize(
            memberId = memberId,
            lessonId = lessonId,
        )
    }

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            // TODO 토스트 안내 제공
            navigateToMemberLessonList(memberId)
        }
    }

    PlusLesson(
        uiState = uiState,
        changeExerciseName = viewModel::changeExerciseName,
        addExerciseSet = viewModel::addExerciseSet,
        removeExerciseSet = viewModel::removeExerciseSet,
        onChangeWeight = viewModel::changeWeight,
        onChangeCount = viewModel::changeCount,
        plusLesson = viewModel::plusLesson,
        onClickClose = popBackStack,
        onClickLoadLesson = navigateToLoadLesson,
        onClickAddExercise = viewModel::addExercise,
        onChangeDate = viewModel::setDateMilliSeconds,
        onChangeAllSet = viewModel::changeAllSet,
        onChangeAllWeight = {},
        onChangeAllCount = {},
    )
}

@Composable
private fun PlusLesson(
    uiState: PlusLessonUiState,
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
    plusLesson: () -> Unit,
    onClickClose: () -> Unit,
    onClickLoadLesson: () -> Unit,
    onClickAddExercise: () -> Unit,
    onChangeDate: (Long?) -> Unit,
    onChangeAllSet: (
        exerciseIndex: Int,
        set: String
    ) -> Unit,
    onChangeAllWeight: (String) -> Unit,
    onChangeAllCount: (String) -> Unit,
) {
    val datePickerVisible = remember { mutableStateOf(false) }

    FitNoteScaffold(
        topBarTitle = stringResource(id = R.string.add_lesson),
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
                            )
                            HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)

                            ItemMainExerciseRow(
                                set = exercise.numberOfSets.toString(),
                                weight = exercise.mainWeight.format(),
                                count = exercise.mainCount.toString(),
                                onChangeAllSet = { set ->
                                    onChangeAllSet(index, set)
                                },
                                onChangeAllWeight = onChangeAllWeight,
                                onChangeAllCount = onChangeAllCount,
                            )

                            HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)
                        },
                        foldText = "세트별 편집",
                        ItemButton = { exerciseSetIndex: Int, _: Exercise.ExerciseSet ->
                            IconButton(
                                onClick = {
                                    removeExerciseSet(index, exerciseSetIndex)
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.trash),
                                    contentDescription = "Back"
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
                onClick = plusLesson,
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
    set: String,
    weight: String,
    count: String,
    onChangeAllSet: (set: String) -> Unit,
    onChangeAllWeight: (String) -> Unit,
    onChangeAllCount: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LessonSetTextField(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            text = set,
            onValueChange = onChangeAllSet,
        )
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)

        LessonWeightTextField(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            text = weight,
            onValueChange = onChangeAllWeight,
        )
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)

        LessonCountTextField(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            text = count,
            onValueChange = onChangeAllCount,
        )
    }
}

@Composable
private fun ItemMainExerciseName(
    text: String,
    onChangeName: (String) -> Unit,
) {
    LessonTextField(
        modifier = Modifier
            .fillMaxWidth()
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
                contentDescription = "Add Exercise"
            )
            DefaultText(
                text = stringResource(id = R.string.btn_add_exercise),
                color = GrayScaleDarkGray1,
                style = LocalFitNoteTypography.current.buttonMedium,
            )
        }
    }
}

private val mockUiState = PlusLessonUiState()

@FigmaPreview
@Composable
private fun PreviewPlusLesson() {
    FitNoteTheme {
        PlusLesson(
            uiState = mockUiState,
            changeExerciseName = { _: Int, _: String -> },
            addExerciseSet = {},
            removeExerciseSet = { _: Int, _: Int -> },
            onChangeWeight = { _: String, _: Int, _: Int -> },
            onChangeCount = { _: String, _: Int, _: Int -> },
            plusLesson = {},
            onClickClose = {},
            onClickLoadLesson = {},
            onClickAddExercise = {},
            onChangeDate = {},
            onChangeAllSet = { _: Int, _: String -> },
            onChangeAllWeight = {},
            onChangeAllCount = {},
        )
    }
}
