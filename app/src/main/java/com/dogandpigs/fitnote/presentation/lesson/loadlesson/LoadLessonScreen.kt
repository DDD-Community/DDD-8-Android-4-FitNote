package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.data.source.remote.model.LessonResponse
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.lesson.addlesson.ExpandableCard
import com.dogandpigs.fitnote.presentation.lesson.addlesson.Routine
import com.dogandpigs.fitnote.presentation.ui.component.*
import com.dogandpigs.fitnote.presentation.ui.theme.*

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun LoadLessonScreen(
    viewModel: LoadLessonViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    viewModel.initialize()
    LoadLesson(state, popBackStack)
}

@Composable
internal fun LoadLesson(
    lessonState: LoadLessonState,
    popBackStack: () -> Unit
) {
    val paddingValues = PaddingValues(16.dp)
    
    FitNoteScaffold(
        topBarTitle = stringResource(id = R.string.load_lesson),
        onClickTopBarNavigationIcon = { popBackStack() },
        topBarNavigationIconImageVector = Icons.Filled.Close,
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .background(Color.White),
        ) {
            Column(modifier = Modifier.padding(paddingValues)) {
                RowTagList(lessonState, 5.dp, PaddingValues(12.dp, 6.dp))
                RoutineList(lessonState)
                CompleteButton(
                    text = stringResource(id = R.string.load_lesson),
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun RowTagList(
    lessonState: LoadLessonState,
    borderRadius: Dp,
    paddingValue: PaddingValues
) {
    val scrollState = rememberScrollState()
    Row(
        Modifier.horizontalScroll(scrollState)
    ) {
        lessonState.memberList.forEach { member ->
            Tag(member.userName ?: "", borderRadius, paddingValue)
            WidthSpacer(width = 10.dp)
        }
    }
}

@Composable
private fun RowExerciseList(
    exerciseList: List<String>,
    borderRadius: Dp,
    paddingValue: PaddingValues
) {
    val scrollState = rememberScrollState()
    Row(
        Modifier.horizontalScroll(scrollState)
    ) {
        exerciseList.forEach { name ->
            Tag(name ?: "", borderRadius, paddingValue)
            WidthSpacer(width = 10.dp)
        }
    }
}

@Composable
private fun Tag(
    text: String,
    borderRadius: Dp,
    paddingValue: PaddingValues
) {
    OutlinedButton(
        colors = ButtonDefaults.outlinedButtonColors(
            disabledContentColor = SubPrimary,
            disabledContainerColor = BrandPrimary,
            contentColor = GrayScaleMidGray3,
            containerColor = GrayScaleLightGray1
        ),
        contentPadding = paddingValue,
        modifier = Modifier
            .defaultMinSize(
                minWidth = ButtonDefaults.MinWidth,
                minHeight = 10.dp
            ),
        onClick = { },
        shape = RoundedCornerShape(borderRadius)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            style = LocalFitNoteTypography.current.buttonSmall,
            color = GrayScaleMidGray3
        )
    }
}

@Composable
private fun RoutineList(lessonsState: LoadLessonState) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
//            .padding(paddingValues)
            .background(color = Color.White)
            .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        lessonsState.exerciseList.forEach {
            ExerciseList(it)
            HeightSpacer(height = 8.dp)
        }
    }
}

@Composable
private fun ExerciseList(description: LessonResponse.Description) {
    val selectedValue = remember { mutableStateOf("") }
    val label = "Item"
    val list = mutableListOf<Routine>().apply {
        add(Routine())
        add(Routine())
        add(Routine())
        add(Routine())
        add(Routine())
    }
    Column(
        modifier = Modifier
            .defaultBorder()
            .padding(16.dp, 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = description.lessonsDate,
                color = GrayScaleMidGray3,
                style = LocalFitNoteTypography.current.buttonMedium
            )
            Spacer(modifier = Modifier.weight(2f))
            RadioButton(
                selected = selectedValue.value == label,
                onClick = { selectedValue.value = label }
            )
        }
        RowExerciseList(description.lessonsName, 50.dp, PaddingValues(10.dp, 6.dp))
        ExpandableCard(
            header = stringResource(id = R.string.view_detail),
            color = Color.LightGray,
            routineView = { routine ->
                Exercise(
                    exercise = Exercise(
                        "벤치프레스",
                        5,
                        80,
                        4
                    )
                )
            },
            routineList = list,
            onClickAdd = {},
            isAddBtnVisible = false
        )
    }
}

@Composable
fun Exercise(exercise: Exercise) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = exercise.title,
            style = LocalFitNoteTypography.current.titleDefault,
            color = GrayScaleDarkGray2
        )
        Spacer(modifier = Modifier.weight(2f))
        Text(
            text = "${exercise.setCount}세트 ${exercise.weight}kg ${exercise.count}회",
            style = LocalFitNoteTypography.current.textDefault,
            color = GrayScaleMidGray3
        )
    }
}

private val mockUiState = LoadLessonState()

@FigmaPreview
@Composable
internal fun PreviewLoadLesson() {
    FitNoteTheme {
        LoadLesson(lessonState = mockUiState, popBackStack = {})
    }
}
