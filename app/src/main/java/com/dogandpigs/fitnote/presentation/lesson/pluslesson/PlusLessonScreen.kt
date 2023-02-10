package com.dogandpigs.fitnote.presentation.lesson.pluslesson

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.lesson.Exercise
import com.dogandpigs.fitnote.presentation.lesson.ExerciseColumn
import com.dogandpigs.fitnote.presentation.lesson.ExerciseSetItemNumberTextField
import com.dogandpigs.fitnote.presentation.lesson.ExerciseSetItemTextField
import com.dogandpigs.fitnote.presentation.ui.component.*
import com.dogandpigs.fitnote.presentation.ui.theme.*
import com.dogandpigs.fitnote.presentation.util.format
import com.google.android.material.datepicker.MaterialDatePicker

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun PlusLessonScreen(
    memberId: Int,
    viewModel: PlusLessonViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToLoadLesson: () -> Unit,
    navigateToAddExercise: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initialize(
            memberId = memberId,
        )
    }

    PlusLesson(
        state = state,
        changeExerciseName = viewModel::changeExerciseName,
        addExerciseSet = viewModel::addExerciseSet,
        removeExerciseSet = viewModel::removeExerciseSet,
        onClickClose = popBackStack,
        onClickLoadLesson = navigateToLoadLesson,
        onClickAddExercise = viewModel::addExercise,
        onClickDateLabel = viewModel::setDateMilliSeconds,
    )
}

@Composable
private fun PlusLesson(
    state: PlusLessonUiState,
    changeExerciseName: (
        index: Int,
        name: String,
    ) -> Unit,
    addExerciseSet: (index: Int) -> Unit,
    removeExerciseSet: (
        exerciseIndex: Int,
        exerciseSetIndex: Int,
    ) -> Unit,
    onClickClose: () -> Unit,
    onClickLoadLesson: () -> Unit,
    onClickAddExercise: () -> Unit,
    onClickDateLabel: (Long) -> Unit,
) {
    FitNoteScaffold(
        topBarTitle = stringResource(id = R.string.add_lesson),
        onClickTopBarNavigationIcon = onClickClose,
        topBarNavigationIconImageVector = Icons.Filled.Close,
        topBarActions = {
            TextButton(onClick = onClickLoadLesson) {
                DefaultText(
                    text = stringResource(id = R.string.btn_load),
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
                    dateMilliSeconds = state.dateMilliSeconds,
                    dateString = state.dateString,
                    onClick = onClickDateLabel,
                )

                HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)

                state.exercises.forEachIndexed { index, exercise ->
                    ExerciseColumn(
                        exercise = exercise,
                        Title = {
                            ExerciseName(
                                name = exercise.name,
                                onClickName = { newValue ->
                                    changeExerciseName(index, newValue)
                                },
                            )
                            HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)

                            ExerciseSetMainRow(
                                set = exercise.numberOfSets.toString(),
                                weight = exercise.mainWeight.format(),
                                count = exercise.mainCount.toString(),
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
                        onChangeWeight = { s: String, i: Int -> },
                        onChangeCount = { s: String, i: Int -> },
                    )
                    HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
                }

                HeightSpacer(height = 18.dp)
                AddExerciseButton(
                    onClickAddExercise = onClickAddExercise,
                )
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing9)
            }

            DefaultBottomLargePositiveButton(
                positiveText = stringResource(id = R.string.btn_save),
                onClickPositive = {
                    //navigateToMemberAdd
                },
            ) {
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
            }
        }
    }
}

@Composable
private fun DateLabel(
    dateMilliSeconds: Long,
    dateString: String,
    onClick: (Long) -> Unit,
) {
    val context = LocalContext.current

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
                showDatePicker(
                    activity = context as AppCompatActivity,
                    dateMilliSeconds = dateMilliSeconds,
                    onClick = onClick,
                )
            }
        )
    }
}

private fun showDatePicker(
    activity: AppCompatActivity,
    dateMilliSeconds: Long,
    onClick: (Long) -> Unit,
) {
    val fm = activity.supportFragmentManager
    val picker = MaterialDatePicker.Builder.datePicker().setSelection(dateMilliSeconds).build()
    fm.let {
        picker.show(fm, picker.toString())
        picker.addOnPositiveButtonClickListener {
            onClick(it)
//            viewModel.setLessonDate()
        }
    }
}

@Composable
private fun ExerciseSetMainRow(
    set: String,
    weight: String,
    count: String,
    onClickSet: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val modifier = Modifier
            .weight(1F)
            .clip(RoundedCornerShape(5.dp))
            .background(GrayScaleLightGray1)
            .padding(
                start = LocalFitNoteSpacing.current.spacing4,
                top = 6.dp,
                bottom = 6.dp,
            )

        ExerciseSetItemNumberTextField(
            modifier = modifier,
            text = set,
            enabled = false,
            suffix = "세트",
        ) {}
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)

        ExerciseSetItemNumberTextField(
            modifier = modifier,
            text = weight.format(),
            enabled = false,
            suffix = "kg",
        ) {}
        WidthSpacer(width = LocalFitNoteSpacing.current.spacing4)

        ExerciseSetItemNumberTextField(
            modifier = modifier,
            text = weight.format(),
            enabled = false,
            suffix = "회",
        ) {}
    }
}

@Composable
private fun ExerciseName(
    name: String,
    onClickName: (String) -> Unit,
) {
    val modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(5.dp))
        .background(GrayScaleLightGray1)
        .padding(
            start = LocalFitNoteSpacing.current.spacing4,
            top = 6.dp,
            bottom = 6.dp,
        )

    ExerciseSetItemTextField(
        modifier = modifier,
        text = name,
    ) {
        onClickName(it)
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
            state = mockUiState,
            changeExerciseName = { index: Int, name: String -> },
            removeExerciseSet = { i: Int, i1: Int -> },
            addExerciseSet = {},
            onClickClose = {},
            onClickLoadLesson = {},
            onClickAddExercise = {},
            onClickDateLabel = {},
        )
    }
}
