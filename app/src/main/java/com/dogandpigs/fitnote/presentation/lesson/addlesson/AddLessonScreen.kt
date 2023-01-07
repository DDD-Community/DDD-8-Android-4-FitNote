package com.dogandpigs.fitnote.presentation.lesson.addlesson

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.*
import com.dogandpigs.fitnote.presentation.ui.component.CloseButton
import com.dogandpigs.fitnote.presentation.ui.component.CompleteButton
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.component.WidthSpacer
import com.dogandpigs.fitnote.presentation.ui.component.defaultBorder
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme
import com.google.android.material.datepicker.MaterialDatePicker

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun AddLessonScreen(
    viewModel: AddLessonViewModel = hiltViewModel(),
    popBackStack: () -> Unit,
    navigateToLoadLesson: () -> Unit,
    navigateToAddExercise: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AddLesson(
        viewModel,
        uiState = uiState,
        onClickClose = popBackStack,
        onClickLoadLesson = navigateToLoadLesson,
        onClickAddExercise = navigateToAddExercise,
    )
}

@Composable
private fun AddLesson(
    viewModel: AddLessonViewModel,
    uiState: AddLessonUiState,
    onClickClose: () -> Unit,
    onClickLoadLesson: () -> Unit,
    onClickAddExercise: () -> Unit,
) {
    val navController = rememberNavController()

    FitNoteScaffold(
        topBarTitle = stringResource(id = R.string.title_add_lesson),
        topBarTitleFontSize = 16.sp,
        onClickTopBarNavigationIcon = { navController.navigateUp() },
        topBarNavigationIconImageVector = Icons.Filled.Close,
        topBarActions = {
            TextButton(onClick = {}) {
                Text(
                    text = stringResource(id = R.string.btn_load),
                    color = colorResource(id = R.color.brand_primary)
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
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
                    uiState.dateString,
                    {},
                )
                AddLessonCard(uiState, viewModel)
            }
            CompleteButton(stringResource(id = R.string.btn_save), onClick = {})
        }
    }
}

@Composable
private fun AddLessonCard(
    uiState: AddLessonUiState,
    viewModel: AddLessonViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            InputLesson(uiState)
            Spacer(modifier = Modifier.height(20.dp))
            AddExercise()
        }
    }
}

@Composable
private fun DateLabel(
    viewModel: AddLessonViewModel,
    dateString: String,
    onClickDate: () -> Unit,
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .height(58.dp)
            .fillMaxWidth()
            .defaultBorder(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        WidthSpacer(width = 16.dp)
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(end = 24.dp),
            text = stringResource(id = R.string.txt_date),
            color = Color.Black,
            fontSize = 12.sp,
        )
        WidthSpacer(width = 24.dp)
        ClickableText(
            text = AnnotatedString(dateString),
            style = TextStyle.Default,
            onClick = {
                showDatePicker(viewModel, context as AppCompatActivity)
            }
        )
    }
}

private fun showDatePicker(viewModel: AddLessonViewModel, activity: AppCompatActivity) {
    val fm = activity.supportFragmentManager
    val picker =
        MaterialDatePicker.Builder.datePicker()
            .setSelection(viewModel.uiState.value.dateMilliSeconds)
            .build()
    fm.let {
        picker.show(fm, picker.toString())
        picker.addOnPositiveButtonClickListener {
            viewModel.setDate(it)
        }
    }
}

@Composable
private fun InputLesson(uiState: AddLessonUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .defaultBorder()
            .padding(16.dp, 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1F)
                    .defaultBorder()
                    .padding(15.dp, 15.dp),
                text = stringResource(id = R.string.txt_exercise_name),
                color = Color.Black,
                fontSize = 12.sp
            )
            CloseButton {

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Routine(false)
        Spacer(modifier = Modifier.height(20.dp))
        ExpandableCard(
            header = stringResource(id = R.string.edit_per_set),
            description = "ㅎㅁㄴㅇㅎㅁㄴㅇㅎ",
            color = Color.LightGray,
            routineView = { Routine(btnRoutineCloseVisibility = true) }
        )
    }
}

@Composable
private fun Routine(btnRoutineCloseVisibility: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1F)
                .defaultBorder()
                .padding(15.dp, 15.dp),
            text = "0세트",
            color = Color.Black,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier
                .weight(1F)
                .defaultBorder()
                .padding(15.dp, 15.dp),
            text = "0kg",
            color = Color.Black,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier
                .weight(1F)
                .defaultBorder()
                .padding(15.dp, 15.dp),
            text = "0회",
            color = Color.Black,
            fontSize = 12.sp
        )
        if (btnRoutineCloseVisibility) {
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(
                onClick = { Log.d("TestTAG", "Routine: ") }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Back"
                )
            }
        }
    }
}

@Composable
private fun AddExercise() {
    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    Box(
        Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
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
                imageVector = Icons.Default.Add,
                tint = Color.Black, // Icon Color
                contentDescription = "Add Exercise"
            )
            Text(
                text = stringResource(id = R.string.btn_add_exercise),
                fontSize = 14.sp,
                color = Color.Black
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
            uiState = mockUiState,
            onClickClose = {},
            onClickLoadLesson = {},
            onClickAddExercise = {},
        )
    }
}
