package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.lesson.memberlesson.SuffixVisualTransformation
import com.dogandpigs.fitnote.presentation.ui.component.CompleteButton
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

@Composable
internal fun AddLessonScreen(
    memberId: Int,
    viewModel: AddLessonViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToLoadLesson: () -> Unit,
    navigateToAddExercise: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    viewModel.setMemberId(memberId)
    AddLesson(
        viewModel,
        state = state,
        onClickClose = popBackStack,
        onClickLoadLesson = navigateToLoadLesson,
        onClickAddExercise = navigateToAddExercise,
    )
}

@Composable
private fun AddLesson(
    viewModel: AddLessonViewModel,
    state: AddLessonState,
    onClickClose: () -> Unit = {},
    onClickLoadLesson: () -> Unit = {},
    onClickAddExercise: () -> Unit = {},
) {
    val navController = rememberNavController()

    FitNoteScaffold(topBarTitle = stringResource(id = R.string.add_lesson),
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
        }) {
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
                    .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                DateLabel(
//                    dateString = uiState.dateString,
//                    onClickShowCalendar = onClickShowCalendar,
//                )
                AddLessonCard(
                    state,
                    viewModel,
                    onClickAddExercise = {
                        viewModel.addLesson(Routine())
                    }
                )
            }
            CompleteButton(stringResource(id = R.string.btn_save), onClick = {
                viewModel.addAllLessons()
            })

//            if (uiState.isShowCalendar) {
//                DefaultCalendarView(
//                    dateMilliSeconds = uiState.dateMilliSeconds,
//                    onChangeCalendar = onChangeCalendar,
//                )
//            }
        }
    }
}

@Composable
private fun AddLessonCard(
    uiState: AddLessonState,
    viewModel: AddLessonViewModel,
    onClickAddExercise: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = LocalFitNoteSpacing.current.spacing4)
        ) {
            HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
            for (i in uiState.exerciseList.indices) {
                InputLesson(uiState.exerciseList[i], viewModel, uiState)
                HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
            }
            AddExercise(onClickAddExercise = onClickAddExercise)
        }
    }
}

@Composable
private fun DateLabel(
    dateString: String,
    onClickShowCalendar: () -> Unit,
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
                onClickShowCalendar()
            }
        )
    }
}

@Composable
private fun InputLesson(
    exercise: Routine,
    viewModel: AddLessonViewModel,
    uiState: AddLessonState
) {
    var exerciseName by remember { mutableStateOf("") }
    val name = exercise.name.ifBlank {
        exerciseName
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .defaultBorder()
            .padding(16.dp, 16.dp),
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            AddLessonTextField(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight(),
                value = name,
                onValueChange = { textValue ->
                    exerciseName = textValue
                    viewModel.setLessonName(exercise.index, exerciseName)
                },
                placeholderValue = "운동명"
            )
            WidthSpacer(width = 10.dp)
            IconButton(onClick = {
                viewModel.removeExercise(exercise.index, exerciseName)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.trash),
                    contentDescription = "Back"
                )
            }
        }
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)

        Routine(
            index = exercise.index,
            Routine(set = 0, weight = 0, count = 0),
            viewModel = viewModel,
            btnRoutineCloseVisibility = false,
        )

        Spacer(modifier = Modifier.height(20.dp))
        ExpandableCard(header = stringResource(id = R.string.edit_per_set),
            color = Color.LightGray,
            routineView = { routine ->
                Routine(
                    index = exercise.index,
                    routine = routine,
                    viewModel = viewModel,
                    btnRoutineCloseVisibility = true,
                    isEdit = true
                )
            },
            routineList = uiState.routineList.toMutableList(),
            onClickAdd = {
                viewModel.addRoutine(
                    Routine(weight = 0, count = 0)
                )
            })
    }
}

@Composable
private fun AddLessonTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderValue: String = "",
    suffix: String? = null,
) {
    BasicTextField(modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        textStyle = LocalFitNoteTypography.current.buttonMedium.copy(
            color = GrayScaleMidGray3,
        ),
        visualTransformation = if (suffix != null) {
            SuffixVisualTransformation(suffix)
        } else {
            VisualTransformation.None
        },
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = GrayScaleLightGray1, shape = RoundedCornerShape(size = 5.dp)
                    )
                    .padding(16.dp, 6.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                if (value.isEmpty() && placeholderValue.isNotEmpty()) {
                    DefaultText(
                        text = placeholderValue,
                        color = GrayScaleMidGray3,
                        style = LocalFitNoteTypography.current.buttonMedium,
                    )
                }
                innerTextField()
            }
        })
}

@Composable
private fun Routine(
    index: Int,
    routine: Routine,
    viewModel: AddLessonViewModel,
    btnRoutineCloseVisibility: Boolean,
    isEdit: Boolean = false
) {
    var set by remember {
        mutableStateOf(routine.set)
    }
    var weight by remember {
        mutableStateOf(routine.weight)
    }
    var count by remember {
        mutableStateOf(routine.count)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isEdit) {
            Text(
                text = "${set}세트",
                style = LocalFitNoteTypography.current.buttonMedium,
                color = GrayScaleMidGray3
            )
        } else {
            AddLessonTextField(
                modifier = Modifier.weight(1F),
                value = "$set",
                onValueChange = { value ->
                    set = value.toIntOrNull() ?: 0
                    viewModel.setLessonSet(index = index, set = set)
                },
                suffix = "세트",
            )
        }
        WidthSpacer(width = 10.dp)
        AddLessonTextField(
            modifier = Modifier
                .weight(1F)
                .wrapContentHeight(),
            value = "$weight",
            onValueChange = { value ->
                weight = value.toIntOrNull() ?: 0
                viewModel.setLessonWeight(index, weight)
            },
            suffix = "kg",
        )
        WidthSpacer(width = 10.dp)
        AddLessonTextField(
            modifier = Modifier.weight(1F),
            value = "$count",
            onValueChange = { value ->
                count = value.toIntOrNull() ?: 0
                viewModel.setLessonCount(index, count)
            },
            suffix = "회",
        )
        if (btnRoutineCloseVisibility) {
            WidthSpacer(width = 10.dp)
            IconButton(onClick = {
                viewModel.removeRoutine(routine.set)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.trash),
//                    imageVector = Icons.Filled.Close,
                    contentDescription = "Back"
                )
            }
        }
    }
}

@Composable
private fun AddExercise(
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

private val mockUiState = AddLessonState()

@FigmaPreview
@Composable
private fun PreviewAddLesson() {
    FitNoteTheme {
        AddLesson(
            viewModel = hiltViewModel(),
            state = mockUiState,
            onClickClose = {},
            onClickLoadLesson = {},
            onClickAddExercise = {},
        )
    }
}
