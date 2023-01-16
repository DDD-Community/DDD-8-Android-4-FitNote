package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.dogandpigs.fitnote.R
import com.dogandpigs.fitnote.presentation.base.FigmaPreview
import com.dogandpigs.fitnote.presentation.ui.component.FitNoteScaffold
import com.dogandpigs.fitnote.presentation.ui.theme.FitNoteTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun LoadLessonScreen(
    viewModel: LoadLessonViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoadLesson(state)
}

@Composable
internal fun LoadLesson(uiState: LoadLessonUiState) {
    val navController = rememberNavController()
    
    FitNoteScaffold(
        topBarTitle = stringResource(id = R.string.title_load_lesson),
        topBarTitleFontSize = 16.sp,
        onClickTopBarNavigationIcon = { navController.navigateUp() },
        topBarNavigationIconImageVector = Icons.Filled.Close,
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column() {
                UserList()
                RoutineList()
            }
        }
    }
}

@Composable
private fun UserList() {
    val scrollState = rememberScrollState()
    Row(Modifier.horizontalScroll(scrollState)) {
        repeat(20) {
            OutlinedButton(
                modifier = Modifier
                    .padding(4.dp, 0.dp)
                    .defaultMinSize(
                        minWidth = ButtonDefaults.MinWidth,
                        minHeight = 10.dp
                    ),
                onClick = { }
            ) {
                Text(
                    text = "[$it]번째 회원",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun RoutineList() {
    val paddingValues = PaddingValues(16.dp)
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .background(color = Color.White)
            .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in 0..10) {
            Routine()
        }
    }
}

@Composable
private fun Routine() {
    Text(text = "routine")
}

private val mockUiState = LoadLessonUiState(
    title = "mock AddLesson title"
)

@FigmaPreview
@Composable
internal fun PreviewLoadLesson() {
    FitNoteTheme {
        LoadLesson(mockUiState)
    }
}