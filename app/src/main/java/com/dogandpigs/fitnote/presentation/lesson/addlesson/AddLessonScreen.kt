package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.lesson.memberlesson.SuffixVisualTransformation
import com.dogandpigs.fitnote.presentation.ui.component.*
import com.dogandpigs.fitnote.presentation.ui.theme.*
import com.google.android.material.datepicker.MaterialDatePicker

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun AddLessonScreen(
    viewModel: AddLessonViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToLoadLesson: () -> Unit,
    navigateToAddExercise: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
    state: AddLessonUiState,
    onClickClose: () -> Unit,
    onClickLoadLesson: () -> Unit,
    onClickAddExercise: () -> Unit,
) {
    val navController = rememberNavController()

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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DateLabel(
                    viewModel,
                    state.dateMilliSeconds,
                    state.dateString
                )
                AddLessonCard(state, viewModel)
            }
            CompleteButton(stringResource(id = R.string.btn_save), onClick = {})
        }
    }
}

@Composable
private fun AddLessonCard(
    uiState: AddLessonUiState, viewModel: AddLessonViewModel
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
            InputLesson(viewModel, uiState)
            HeightSpacer(height = LocalFitNoteSpacing.current.spacing5)
            AddExercise()
        }
    }
}

@Composable
private fun DateLabel(
    viewModel: AddLessonViewModel,
    dateMilliSeconds: Long,
    dateString: String,
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
        ClickableText(
            text = AnnotatedString(dateString),
            style = LocalFitNoteTypography.current.textDefault,
            onClick = {
                showDatePicker(
                    viewModel,
                    dateMilliSeconds,
                    context as AppCompatActivity
                )
            }
        )
    }
}

private fun showDatePicker(
    viewModel: AddLessonViewModel,
    dateMilliSeconds: Long,
    activity: AppCompatActivity
) {
    val fm = activity.supportFragmentManager
    val picker = MaterialDatePicker.Builder.datePicker().setSelection(dateMilliSeconds).build()
    fm.let {
        picker.show(fm, picker.toString())
        picker.addOnPositiveButtonClickListener {
            viewModel.setState { copy(dateMilliSeconds = it) }
        }
    }
}

@Composable
private fun InputLesson(
    viewModel: AddLessonViewModel, uiState: AddLessonUiState
) {
    var exerciseName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .defaultBorder()
            .padding(16.dp, 16.dp),
    ) {
        AddLessonTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            value = exerciseName,
            onValueChange = { textValue ->
                exerciseName = textValue
            },
            placeholderValue = "운동명"
        )
        HeightSpacer(height = LocalFitNoteSpacing.current.spacing4)

        Routine(
            Routine(0, 0, 0),
            viewModel = viewModel,
            btnRoutineCloseVisibility = false,
        )

        Spacer(modifier = Modifier.height(20.dp))
        ExpandableCard(
            header = stringResource(id = R.string.edit_per_set),
            color = Color.LightGray,
            routineView = { routine ->
                Routine(
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
            }
        )
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
    BasicTextField(
        modifier = modifier,
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
                        color = GrayScaleLightGray1,
                        shape = RoundedCornerShape(size = 5.dp)
                    )
                    .padding(16.dp, 6.dp),
                verticalAlignment = Alignment.CenterVertically
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
        }
    )
}

@Composable
private fun Routine(
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
                modifier = Modifier
                    .weight(1F),
                value = "$set",
                onValueChange = { value ->
                    set = value.toIntOrNull() ?: 0
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
            },
            suffix = "kg",
        )
        WidthSpacer(width = 10.dp)
        AddLessonTextField(
            modifier = Modifier
                .weight(1F),
            value = "$count",
            onValueChange = { value ->
                count = value.toIntOrNull() ?: 0
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
private fun AddExercise() {
    val stroke = Stroke(
        width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    Box(
        Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            drawRoundRect(
                color = Color.LightGray,
                style = stroke,
                cornerRadius = CornerRadius(10.dp.toPx())
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

private val mockUiState = AddLessonUiState(
    title = "mock AddLesson title"
)

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
